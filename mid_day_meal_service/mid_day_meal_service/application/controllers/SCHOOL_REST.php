<?php

defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

class SCHOOL_REST extends REST_Controller{

    public function __construct() {
        parent::__construct();
        $this->load->model('School_model');
         $this->load->model('Manageorderotpmodel');
    }    

    public function data_get($id_param = NULL){
        $arr = array('conditions'=>$this->_get_args);
        $login = $this->School_model->getRows($arr);
       

        if($login) {           
            $results['login'] = $login;
            $this->response($results, 200);
        } else {
               $this->response(NULL, 404);
        }
    }


     public function data2_get($id_param = NULL){
        $arr = array('conditions'=>$this->_get_args);
        $login = $this->School_model->getRows2($arr);
        //$finalArray = array();
        /*if ($login) {
            $final = array();
            $size = count($login);
            for ($i=0; $i <$size ; $i++) { 
                 # code...
                $final[$i]['id'] = $login[$i]['id'];
                $final[$i]['name'] = $login[$i]['name'];
                $final[$i]['isUser'] = 
         } 
        }*/
       

        if($login) {           
            $results['data'] = $login;
            $this->response($results, 200);
        } else {
               $this->response(NULL, 404);
        }
    }


    

function data_post() {
       
       $registration_data = array();
        $registration_data['school_id'] = $this->input->post('school_id');
        $registration_data['feedback_manager_id'] = $this->input->post('feedback_manager_id');
        $registration_data['user_id'] = $this->input->post('user_id');
        $registration_data['feedback'] = $this->input->post('feedback');
        $registration_data['comments'] = $this->input->post('comments');

        $registration_data['feedback_head_id'] = $this->input->post('feedback_head_id');
        $registration_data['datetime'] = date("Y-m-d H:i:s");
        $registration_data['month'] = date('M');

        $response =array();
       
       
      if(array_key_exists("id",$this->input->input_stream())) {
            
            $id = $this->input->post('id');                         
            $results = $this->School_model->update($id,$registration_data);
            
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
            $results = $this->School_model->insert($registration_data);
        
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