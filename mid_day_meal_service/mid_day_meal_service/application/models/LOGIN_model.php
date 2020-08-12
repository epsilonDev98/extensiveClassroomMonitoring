<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class LOGIN_model extends CI_Model{
    function __construct() {
        $this->userTbl = 'feedback_manager';
        $this->userTb2 = 'school_master';
		$this->load->database();
    }

    function getRows($params = array()){
       $type = $params["conditions"]["type"];
       $user_name = $params["conditions"]["user_name"];
       $password = $params["conditions"]["password"];
       if ($type=="1") {
           # code...
        $this->db->select('*');
        $this->db->from($this->userTbl);
         $this->db->where('user_name',$user_name);
          $this->db->where('password',$password);
       }else{
        $this->db->select('*');
        $this->db->from($this->userTb2);
         $this->db->where('user_name',$user_name);
          $this->db->where('password',$password);
       }
        
        //fetch data by conditions
            $query = $this->db->get();
             
				$result = ($query->num_rows() > 0)?$query->result_array():FALSE;
            
        
        return $result;
    }








public function insert($data = array()) { 
        //insert user data to users table
      //  print_r($data); die();
    
        $insert = $this->db->insert($this->userTbl, $data);

        return $insert;
          
    }
function update($id,$data = array()) {
        //printf($user_id);
        //print_r($data);
        $this->db->where('id', $id);
        $result = $this->db->update($this->userTbl, $data);
        return $result;
    }  

function updatePassword($mobile,$data = array()) {
        //printf($user_id);
        //print_r($data);
        $this->db->where('mobile', $mobile);
        $result = $this->db->update($this->userTbl, $data);
        return $result;
    }  
    
}