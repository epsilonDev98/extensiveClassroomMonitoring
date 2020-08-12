<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class School_model extends CI_Model{
    function __construct() {
        $this->userTbl = 'school_master';
        $this->userTb2 = 'assign_feedback_manager_school';
        $this->userTb3 = 'feedback_head_master';
        $this->userTb4 = 'feedback_master';

		$this->load->database();
    }



    function getRows($params = array()){
       
        $this->db->select('*');
        $this->db->from($this->userTb2);
        $this->db->join($this->userTbl,'assign_feedback_manager_school.school_id = school_master.id');
        //fetch data by conditions
    
        if(array_key_exists("conditions",$params)){
            foreach ($params['conditions'] as $key => $value) {
                $this->db->where($key,$value);
                
            }
        }
        
        if(array_key_exists("id",$params)){
            $this->db->where('id',$params['id']);
            $query = $this->db->get();
            $result = $query->row_array();
		}            
            $query = $this->db->get();
             
				$result = ($query->num_rows() > 0)?$query->result_array():FALSE;
            
        
        return $result;
    }



function getRows2($params = array()){
       
        $userId = $params["conditions"]["userId"];
        $anc=date('M');
        $this->db->select('feedback_head_master.id,feedback_head_master.name');
        $this->db->from($this->userTb3);



        //Select name from feedback_head_master where id not in (select feedback_head_id from feedback_master)
       // $this->db->join($this->userTb4,'feedback_head_master.id = feedback_master.feedback_head_id');
        $this->db->where("id NOT IN (select feedback_head_id from feedback_master where month='$anc' AND school_id=$userId)");
       // $this->db->join($this->userTbl,'feedback_master.feedback_manager_id = feedback_head_master.id');
        //fetch data by conditionsuser_id
    
        /*if(array_key_exists("conditions",$params)){
            foreach ($params['conditions'] as $key => $value) {
                $this->db->where($key,$value);
                
            }
        }
        
        if(array_key_exists("id",$params)){
            $this->db->where('id',$params['id']);
            $query = $this->db->get();
            $result = $query->row_array();
        }            */
            $query = $this->db->get();
             
                $result = ($query->num_rows() > 0)?$query->result_array():FALSE;


            
        
        return $result;
    }



function getIsAttanadance($id,$date){
     $this->db->select('*');
     $this->db->where('date',$date);
     $this->db->where('student_id',$id);
     $check_user = $this->db->get($this->userTb2)->row();
     if ($check_user) {
         # code...
        return true;
     }else{
        return FALSE;
     }
    
    }


    function dislike($id){
      $query = $this->db->query('SELECT * FROM dislike_master where answer_id ='.$id);

    return $query->num_rows();
       
    }



public function insert($data = array()) { 
       
         $this->db->insert($this->userTb4, $data);
         $insert_id = $this->db->insert_id();
        return  $insert_id;   
    }


public function insertAttendance($data = array()) { 
    
        $insert = $this->db->insert($this->userTb2, $data);

        return $insert;
          
    }

    public function student_assign_school($data = array()) { 
    
        $insert = $this->db->insert($this->userTb3, $data);

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