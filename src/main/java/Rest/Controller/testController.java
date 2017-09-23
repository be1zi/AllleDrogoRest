package Rest.Controller;

import Rest.DAO.TestDao;
import Rest.Model.TestModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/test")
public class testController {

    private final TestDao testDao;

    public testController(TestDao testDao) {
        this.testDao = testDao;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<TestModel> get(){

        List<TestModel> list = (List<TestModel>) testDao.findAll();

        return list;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void test1(@RequestParam(value = "name") String name){

        TestModel t1 = new TestModel();
        t1.setName(name);
        testDao.save(t1);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TestModel> getResult(@PathVariable("id") Long id) {

        Optional<TestModel> result = testDao.findById(id);

        TestModel testModel = new TestModel();
        testModel.setId(result.map(TestModel::getId).orElse(Long.valueOf(0)));
        testModel.setName(result.map(TestModel::getName).orElse(""));

        return new ResponseEntity<>(testModel, new HttpHeaders(), OK);
    }

    @RequestMapping(value = "/addtest",method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody TestModel testModel){
        TestModel t1 = new TestModel();
        t1.setName(testModel.getName());
        testDao.save(t1);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
