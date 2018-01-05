package Rest.Controller;

import Rest.DAO.MessageRepository;
import Rest.DAO.SingleMessageRepository;
import Rest.Model.MessageModel;
import Rest.Model.SingleMessageModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageRepository messageRepository;
    private final SingleMessageRepository singleMessageRepository;

    public MessageController(MessageRepository messageRepository, SingleMessageRepository singleMessageRepository){
        this.messageRepository = messageRepository;
        this.singleMessageRepository = singleMessageRepository;
    }

    @RequestMapping(value = "/addFirst", method = RequestMethod.POST)
    public ResponseEntity<Long> addFirst(@RequestBody MessageModel messageModel){

        MessageModel message = messageRepository.findByTopicAndOwnerLoginAndSenderLogin(messageModel.getTopic(), messageModel.getOwnerLogin(), messageModel.getSenderLogin());


        if(message == null){
            message = new MessageModel();

            messageRepository.save(messageModel);
            message = messageRepository.findByTopicAndOwnerLoginAndSenderLogin(messageModel.getTopic(), messageModel.getOwnerLogin(), messageModel.getSenderLogin());
        }

        return new ResponseEntity<>(message.getId(), new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Boolean> add(@RequestBody SingleMessageModel singleMessageModel){

        Optional<MessageModel> result = messageRepository.findById(singleMessageModel.getMessageModelId());
        MessageModel message = new MessageModel();
        message.setId(result.map(MessageModel::getId).orElse(Long.valueOf(0)));
        message.setTopic(result.map(MessageModel::getTopic).orElse(null));
        message.setDate(result.map(MessageModel::getDate).orElse(null));
        message.setOwnerLogin(result.map(MessageModel::getOwnerLogin).orElse(null));
        message.setSenderLogin(result.map(MessageModel::getSenderLogin).orElse(null));
        message.setItemNumber(result.map(MessageModel::getItemNumber).orElse(0));
        message.setViewed(result.map(MessageModel::isViewed).orElse(false));
        message.setSingleMessageModels(result.map(MessageModel::getSingleMessageModels).orElse(null));


        if(message == null)
            return new ResponseEntity<>(false, new HttpHeaders(), HttpStatus.OK);

        int itemsNumber = message.getItemNumber() + 1;
        message.setItemNumber(itemsNumber);
        message.getSingleMessageModels().add(singleMessageModel);
        message.setDate(Calendar.getInstance());

        singleMessageRepository.save(singleMessageModel);
        messageRepository.save(message);

        return new ResponseEntity<>(true, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/getMessageList", method = RequestMethod.POST)
    public ResponseEntity<MessageModel[]> getMessageList(@RequestBody String userLogin){

        List<MessageModel> ownerList = messageRepository.findAllByOwnerLogin(userLogin);
        List<MessageModel> senderList = messageRepository.findAllBySenderLogin(userLogin);

        if((ownerList == null || ownerList.size() == 0) && (senderList == null || senderList.size() == 0))
            return new ResponseEntity<>( new MessageModel[0], new HttpHeaders(), HttpStatus.OK);

        MessageModel[] result = new MessageModel[ownerList.size() + senderList.size()];

        int iterator = 0;
        if(ownerList != null && ownerList.size() != 0) {
            for (int i = 0; i < ownerList.size(); i++){
                result[i] = ownerList.get(i);
                iterator++;
            }
        }

        if(senderList != null && senderList.size() != 0){
            for(int i=0;i<senderList.size(); i++ ){
                result[iterator] = senderList.get(i);
                iterator++;
            }
        }

        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);

    }

//    @RequestMapping(value = "/getSingleMessagesList", method = RequestMethod.POST)
//    public ResponseEntity<MessageModel[]> getSingleMessagesList(@RequestBody Map<String, String> map){
//
//        String topic = map.get("topic");
//        String ownerLogin = map.get("ownerLogin");
//        String senderLogin = map.get("senderLogin");
//
//    }
}
