package com.example.easywork;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.easywork.common.Constants.Msg;
import com.example.easywork.common.enums.Status;
import com.example.easywork.model.Work;
import com.example.easywork.model.response.ResponseError;
import com.example.easywork.model.response.ResponseListWork;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EasyWorkApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api";
    }
    //Object gson to use utility with json format
    Gson gson = new Gson();

    /**
     * Test context load
     */
    @Test
    public void contextLoads() {

    }
    /**
     * Test Invalid API URL Get all work
     * When API URL Get all work invalid. 
     * Result is include : 
     *                      httpstatus : 404 
     */
    @Test
    public void testInvalidAPIGetAllWork() {
        // Case invalid api URL get all work
    	 HttpHeaders headers = new HttpHeaders();
         HttpEntity<String> entity = new HttpEntity<String>(null, headers);
         ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/worksGetAll", HttpMethod.GET, entity,
                 String.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    /**
     * Test API URL Get all work
     * When input correct URL API Get all work
     * Result is include : 
     *                      httpstatus : 200 
     *                      body : has content
     */
    @Test
    public void testGetAllworks() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/works", HttpMethod.GET, entity,
                String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }
    
    /**
     * Test Invalid API URL create work
     * When API URL create work invalid. 
     * Result is include : 
     *                      httpstatus : 404 
     */
    @Test
    public void testInvalidAPICreateWork() {
        // Case invalid API URL create work
    	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
         Work work = new Work();
         work.setWorkName("Java");
         try {
             work.setStartingDate(sdf.parse("18/08/2018"));
         } catch (ParseException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         try {
             work.setEndingDate(sdf.parse("20/08/2018"));
         } catch (ParseException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         work.setStatus(Status.PLAINING);
         ResponseEntity<Work> postResponse = restTemplate.postForEntity(getRootUrl() + "/worksCreate", work, Work.class);
        assertEquals(postResponse.getStatusCode(), HttpStatus.NOT_FOUND);
    }
    
    /**
     * Test API URL create work
     * When input correct API URL create work
     * Result is include : 
     *                      httpstatus : 200 
     *                      body : has content
     */
    @Test
    public void testCreatework() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Work work = new Work();
        work.setWorkName("Java");
        try {
            work.setStartingDate(sdf.parse("18/08/2018"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            work.setEndingDate(sdf.parse("20/08/2018"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        work.setStatus(Status.PLAINING);
        ResponseEntity<Work> postResponse = restTemplate.postForEntity(getRootUrl() + "/works", work, Work.class);
        assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        assertNotNull(postResponse.getBody());
    }

    /**
     * Test Invalid API URL Get work by id
     * When API URL Get work by id invalid. 
     * Result is include : 
     *                      httpstatus : 404 
     */
    @Test
    public void testInvalidAPIGetworkById() {
        // Case invalid API URL Get work by id
    	 HttpHeaders headers = new HttpHeaders();
         HttpEntity<String> entity = new HttpEntity<String>(null, headers);
         ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/worksId/1", HttpMethod.GET, entity,
                 String.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    /**
     * Test get work by id
     */
    @Test
    public void testGetworkById() {
        Work work = restTemplate.getForObject(getRootUrl() + "/works/1", Work.class);
        System.out.println(work.getWorkName());
        assertNotNull(work);
    }

    /**
     * Test Invalid API URL Update work
     * When API URL Update work invalid. 
     * Result is include : 
     *                      httpstatus : 404 
     */
    @Test
    public void testInvalidAPIUpdatework() {
        // Case invalid API URL Get work by id
    	int id = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Work workUpdate = restTemplate.getForObject(getRootUrl() + "/works/" + id, Work.class);
        workUpdate.setWorkName("Python");
        try {
            workUpdate.setStartingDate(sdf.parse("20/08/2018"));
        } catch (ParseException e) {
        }
        try {
            workUpdate.setEndingDate(sdf.parse("18/08/2018"));
        } catch (ParseException e) {
        }
         workUpdate.setStatus(Status.COMPLETE);
        restTemplate.put(getRootUrl() + "/worksUpdate/" + id, workUpdate);
        ResponseEntity<Work> postResponse = restTemplate.postForEntity(getRootUrl() + "/worksUpdate"+ id, workUpdate, Work.class);
        assertEquals(postResponse.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    /**
     * Test update work
     * When delete work, it need check again workId was deleted
     * Result is include : 
     *                      workId was deleted 
     */
    @Test
    public void testUpdatework() {
        int id = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Work workUpdate = restTemplate.getForObject(getRootUrl() + "/works/" + id, Work.class);
        workUpdate.setWorkName("Python");
        try {
            workUpdate.setStartingDate(sdf.parse("20/08/2018"));
        } catch (ParseException e) {
        }
        try {
            workUpdate.setEndingDate(sdf.parse("18/08/2018"));
        } catch (ParseException e) {
        }
         workUpdate.setStatus(Status.COMPLETE);
        restTemplate.put(getRootUrl() + "/works/" + id, workUpdate);
        Work updatedwork = restTemplate.getForObject(getRootUrl() + "/works/" + id, Work.class);
        assertNotNull(updatedwork);
    }

    /**
     * Test delete work
     * When delete work, it need check again workId was deleted
     * Result is include : 
     *                      workId was deleted 
     */
    @Test
    public void testDeletework() {
    	// Create new work with id is 2 and then delete work with id is 1
    	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
         Work work_php = new Work();
         work_php.setWorkName("PHP");
         try {
        	 work_php.setStartingDate(sdf.parse("10/08/2018"));
         } catch (ParseException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         try {
        	 work_php.setEndingDate(sdf.parse("11/08/2018"));
         } catch (ParseException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         work_php.setStatus(Status.DOING);
         ResponseEntity<Work> postResponse = restTemplate.postForEntity(getRootUrl() + "/works", work_php, Work.class);
         assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        int idDelete = 1;
        Work workDelete = restTemplate.getForObject(getRootUrl() + "/works/" + idDelete, Work.class);
        assertNotNull(workDelete);
        restTemplate.delete(getRootUrl() + "/works/" + idDelete);
        workDelete = restTemplate.getForObject(getRootUrl() + "/works/" + idDelete, Work.class);
        assertEquals(workDelete.getId(), null);
    }

    /**
     * Test Invalid Sort Search Work 
     * When param direction invalid. 
     * Result is include : 
     *                      httpstatus : 412 
     *                      message : Invalid sort direction
     */
    @Test
    public void testInvalidSortSearchWork() {
        // Case sort with param direction invalid
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/works"  +"?orderBy=id&direction=AAAA&page=0&size=10", HttpMethod.GET, entity,
                String.class);
        ResponseError respnoseError = gson.fromJson(response.getBody(), ResponseError.class);
        assertEquals(response.getStatusCode(), HttpStatus.PRECONDITION_FAILED);
        assertEquals(respnoseError.getMessage(), Msg.INVALID_SORT_MSG);
    }
    

    /**
     * Test Invalid Order by Search Work 
     * When param orderBy invalid. 
     * Result is include : 
     *                     httpstatus : 412 
     *                     message : Invalid Order by direction
     */
    @Test
    public void testInvalidOrderBySearchWork() {
        // Case sort with param direction invalid
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/works"  +"?orderBy=AAA&direction=ASC&page=0&size=10", HttpMethod.GET, entity,
                String.class);
        ResponseError respnoseError = gson.fromJson(response.getBody(), ResponseError.class);
        assertEquals(response.getStatusCode(), HttpStatus.PRECONDITION_FAILED);
        assertEquals(respnoseError.getMessage(), Msg.INVALID_ORDER_BY_MSG);
    }

    /**
     * Test Valid Pagin and Sort Search Work 
     * When param orderBy and direction valid. 
     * Result is include : 
     *                     httpstatus : 200 
     *                     content : not null
     */
    @Test
    public void testValidPagingAndSortSearchWork() {
        // Case sort with param direction invalid
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/works"  +"?orderBy=id&direction=ASC&page=0&size=10", HttpMethod.GET, entity,
                String.class);
        ResponseListWork resposeBody = gson.fromJson(response.getBody(), ResponseListWork.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(resposeBody.getContent());
    }
}
