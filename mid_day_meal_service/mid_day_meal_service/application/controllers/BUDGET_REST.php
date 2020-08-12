<?php

defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

class BUDGET_REST extends REST_Controller{

    public function __construct() {
        parent::__construct();
        $this->load->model('Budget_model');
         $this->load->model('Manageorderotpmodel');
    }    

    public function data_get($id_param = NULL){
        $arr = array('conditions'=>$this->_get_args);
        $login = $this->Budget_model->getRows($arr);
       

        if($login) {           
            $results['login'] = $login;
            $this->response($results, 200);
        } else {
               $this->response(NULL, 404);
        }
    }


    public function student_get($id_param = NULL){
        $arr = array('conditions'=>$this->_get_args);
        $login = $this->Budget_model->getStudentRows($arr);
       

        if($login) {           
            $results['login'] = $login;
            $this->response($results, 200);
        } else {
               $this->response(NULL, 404);
        }
    }


    public function studentDetails_get($id_param = NULL){
        $arr = array('conditions'=>$this->_get_args);
        $login = $this->Budget_model->getStudentDetailsRows($arr);
       

        if($login) {           
            $results['student'] = $login;
            $this->response($results, 200);
        } else {
               $this->response(NULL, 404);
        }
    }

function data_post() {
       
       $registration_data = array();
       $otp = rand(100000, 999999);
        $registration_data['fname'] = $this->input->post('first_name');
        $registration_data['lname'] = $this->input->post('last_name');
        $registration_data['password'] = $this->input->post('password');
        $registration_data['email'] = $this->input->post('email');
        $registration_data['mobile'] = $this->input->post('mobile');
        $registration_data['gender'] = $this->input->post('gender');
        $registration_data['photo'] = $this->input->post('photo');
        $registration_data['house_no'] = $this->input->post('house_no');
        $registration_data['city'] = $this->input->post('city');
        $registration_data['state'] = $this->input->post('state');
        $registration_data['pinCode'] = $this->input->post('pinCode');
        $registration_data['designation'] = $this->input->post('designation');
        $registration_data['company_name'] = $this->input->post('company_name');
        $registration_data['otp'] =$otp; 
        $registration_data['active'] = $this->input->post('active');

        $response =array();
       
       
      if(array_key_exists("id",$this->input->input_stream())) {
            
            $id = $this->input->post('id');                         
            $results = $this->Budget_model->update($id,$registration_data);
            
            if($results=== TRUE) {       
                $response['status'] = "200";
                $response['msg'] ="update success";  
                $response1[] = $response;
                $res['msg'] = $response1;
                 $this->response($res, 200);    
            } else {
                $response =array();
                $response['status'] = "400";
                $response['msg'] ="update Fail"; 
                $response1[] = $response;
                $res['msg'] = $response1;
                 $this->response($res, 404);  
            }
        
        }else{
            $results = $this->Budget_model->insert($registration_data);
        
            if($results=== TRUE) {       
                $response['status'] = "200";
                $response['msg'] ="Insert success";  
                $response1[] = $response;
                $res['msg'] = $response1;
                 $this->response($res, 200);    
            } else {
    
                $response['status'] = "400";
                $response['msg'] ="Insert Fail"; 
                $response1[] = $response;
                $res['msg'] = $response1;
                 $this->response($res, 404);  
            }
            
        }

}




	 
}