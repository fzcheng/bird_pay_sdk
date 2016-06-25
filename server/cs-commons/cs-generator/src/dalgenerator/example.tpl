<?php
#{requires}

if(!class_exists('dal_#{db}_entity_#{className}')){
  /**
  * 数据库表查询
  */
  class dal_#{db}_entity_#{className} {
    /**
     * 
     * 指定排序字段, 例如:按字段t1升序, 则值为: t1 asc
     * 
     * @var string
     */
    public $orderByClause;
    	
    /**
     * 是否执行distinct查询. 默认值: false
     * 
     * @var boolean
     */
    public $distinct = false;
    
    /**
     * 查询条件集合
     *
     * @var array|dal_#{db}_entity_#{className}Criteria
     */
    public $oredCriteria = array ();
    
    /**
     * 创建一个'OR'查询条件
     * 
     * @return dal_#{db}_entity_#{className}Criteria
     */
    public function ored() {
    	$criteria = new dal_#{db}_entity_#{className}Criteria();
    	
    	$count  =  count($this->oredCriteria);
    	$this->oredCriteria[$count]=$criteria;
    	
    	return $criteria;
    }
    
    
    /**
     * 创建一个查询条件
     * 
     * @return dal_#{db}_entity_#{className}Criteria
     */
    public function createCriteria(){
    	$criteria = new dal_#{db}_entity_#{className}Criteria();
    	
    	$count  =  count($this->oredCriteria);
    	
    	
    	if($count==0){			
    		$this->oredCriteria[0]=$criteria;
    	}
    	
    	return $criteria;
    }
  }
  	
  /**
  * 查询条件定义
  */
  class dal_#{db}_entity_#{className}Criteria extends dal_common_criteria{
  		 		 
    #{criterions}  	 
  }
}
?>