<?php

defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

class STUDENT_REST extends REST_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model('Student_model');
         $this->load->model('Manageorderotpmodel');
    }    

    public function data_get($id_param = NULL){
        $arr = array('conditions'=>$this->_get_args);
        $login = $this->Student_model->getRows($arr);
       

        if($login) {      

        $final = array();
        $size = count($login);
        for ($i=0; $i <$size ; $i++) { 
             # code...
            $final[$i]['first_name'] = $login[$i]['first_name'];
            $final[$i]['last_name'] = $login[$i]['last_name'];
            $final[$i]['father_name'] = $login[$i]['father_name'];
            $final[$i]['isAttandance'] = $this->Student_model->getIsAttanadance($login[$i]['id'],date("Y-m-d"));
            $final[$i]['photo'] = $login[$i]['photo'];
            $final[$i]['id'] = $login[$i]['id'];
            $final[$i]['school_id'] = $login[$i]['school_id'];
            $final[$i]['class'] = $login[$i]['class'];
            //$final[$i]['team2'] = $res1[$i]['team2'];
         } 
            $results['data'] = $final;
            $this->response($results, 200);
        } else {
               $this->response(NULL, 404);
        }
    }


    public function student_get($id_param = NULL){
        $arr = array('conditions'=>$this->_get_args);
        $login = $this->Student_model->getStudentRows($arr);
       

        if($login) {           
            $results['login'] = $login;
            $this->response($results, 200);
        } else {
               $this->response(NULL, 404);
        }
    }

     public function dataClass_get($id_param = NULL){
        $arr = array('conditions'=>$this->_get_args);
        $login = $this->Student_model->getClass($arr);
       

        if($login) {           
            $results['class'] = $login;
            $this->response($results, 200);
        } else {
               $this->response(NULL, 404);
        }
    }


    public function studentDetails_get($id_param = NULL){
        $arr = array('conditions'=>$this->_get_args);
        $login = $this->Student_model->getStudentDetailsRows($arr);
       

        if($login) {           
            $results['student'] = $login;
            $this->response($results, 200);
        } else {
               $this->response(NULL, 404);
        }
    }

function data_post() {
       
       $registration_data = array();
        $registration_data['school_id'] = $this->input->post('school_id');
        $registration_data['student_id'] = $this->input->post('student_id');
        $registration_data['date'] = date("Y-m-d");

        $response =array();
       
       
      if(array_key_exists("id",$this->input->input_stream())) {
            
            $id = $this->input->post('id');                         
            $results = $this->Student_model->update($id,$registration_data);
            
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
            $results = $this->Student_model->insertAttendance($registration_data);
        
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


function enroll_post() {
       
       $registration_data = array();
        $school_id = $this->input->post('school_id');
        $registration_data['first_name'] = $this->input->post('first_name');
        $registration_data['last_name'] = $this->input->post('last_name');                    
        $registration_data['father_name'] = $this->input->post('father_name');                    
        $registration_data['mother_name'] = $this->input->post('mother_name');                    
        $registration_data['father_income'] = $this->input->post('father_income');                    
        $registration_data['total_family_member'] = $this->input->post('total_family_member');                    
        $registration_data['d_o_b'] = $this->input->post('d_o_b');                    
        $registration_data['caste'] = $this->input->post('caste');                    
        $registration_data['class'] = $this->input->post('class');                    
        $registration_data['father_aadhar_no'] = $this->input->post('father_aadhar_no');                    
        $registration_data['photo'] = $this->input->post('photo');    
        $registration_data['gender'] = $this->input->post('gender');                    
        $registration_data['isActive'] = "1";                    
        $results = $this->Student_model->insert($registration_data);
        if ($results) {
            $registration_data2 = array();
            $registration_data2['school_id'] = $school_id;
            $registration_data2['student_id'] = $results;
            $results2 = $this->Student_model->student_assign_school($registration_data2);
            if($results2) {       
                $response['status'] = "200";
                $response['msg'] ="success";  
                $response1[] = $response;
                $res['msg'] = $response1;
                 $this->response($res, 200);    
            } else {
                $response =array();
                $response['status'] = "400";
                $response['msg'] =" Fail"; 
                $response1[] = $response;
                $res['msg'] = $response1;
                 $this->response($res, 404);  
            }
        }else{
            $response =array();
                $response['status'] = "400";
                $response['msg'] ="fail"; 
                $response1[] = $response;
                $res['msg'] = $response1;
                 $this->response($res, 404); 
        }
        }

            
       

}
