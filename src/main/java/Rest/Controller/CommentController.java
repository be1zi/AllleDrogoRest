package Rest.Controller;

import Rest.DAO.AccountRepository;
import Rest.DAO.CommentRepository;
import Rest.DAO.TransactionRepository;
import Rest.DAO.UserRepository;
import Rest.Helpers.Comment;
import Rest.Model.AccountModel;
import Rest.Model.CommentModel;
import Rest.Model.TransactionModel;
import Rest.Model.UserModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    private final CommentRepository commentRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public CommentController(CommentRepository commentRepository, TransactionRepository transactionRepository, UserRepository userRepository, AccountRepository accountRepository){
        this.commentRepository = commentRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @RequestMapping("/add")
    public ResponseEntity<Boolean> setCommern(@RequestBody CommentModel comment){

        TransactionModel transaction = transactionRepository.findByIdAndUserLogin(comment.getTransactionId(), comment.getSenderLogin());

        if(transaction == null)
            return new ResponseEntity<>(false, new HttpHeaders(), HttpStatus.OK);

        UserModel user = userRepository.findByLogin(transaction.getOwnerLogin());

        if(user == null)
            return new ResponseEntity<>(false, new HttpHeaders(), HttpStatus.OK);

        AccountModel account = user.getAccountModel();

        if(account == null)
            return new ResponseEntity<>(false, new HttpHeaders(), HttpStatus.OK);


        List<CommentModel> comments = account.getCommentList();

        if(comments == null)
            comments = new ArrayList<>();

        comments.add(comment);
        transaction.setCommentSet(true);
        account.setCommentList(comments);

        double sum = 0;
        for(CommentModel cM: account.getCommentList()){
            sum+= cM.getRate();
        }
        int tmpNumber = account.getCommentList().size();

        if(tmpNumber == 0)
            tmpNumber = 1;

        account.setMark(sum/tmpNumber);

        commentRepository.save(comment);
        transactionRepository.save(transaction);
        accountRepository.save(account);

        return new ResponseEntity<>(true, new HttpHeaders(), HttpStatus.OK);

    }
}
