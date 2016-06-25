<?php
if(!class_exists('dal_common_criterion')){
	/**
	 * SQL查询条件
	 */
	class dal_common_criterion {
		
		public $condition;
		
		public $value;
		
		public $secondValue;
		
		public $noValue = false;
		
		public $singleValue = false;
		
		public $betweenValue = false;
		
		public $listValue = false;
		
		public $typeHandler;
		
		public function __construct($condition, $value, $secondValue, $noValue,$typeHandler = null) {
			$this->condition = $condition;
			$this->typeHandler = $typeHandler;
			
			$this->value = $value;
			$this->secondValue = $secondValue;
			
			$this->noValue = $noValue;			 
			
			if(is_null($secondValue)){
				$this->betweenValue = false;
			}else{
				$this->betweenValue = true;
			} 
			 
			//echo "Contition: ".$condition.", Value:".$value."NoValue: ".$this->noValue;
			
			if (is_array ( $value )) {
				$this->listValue = true;
			} else {
				if($this->betweenValue){
					$this->singleValue = false;
				}else{
					$this->singleValue = true;
				}
			}
		}
	}
	
	/**
	 * 查询设置
	 */
	class dal_common_criteria {
		/**
		 *
		 * @var array|dal_common_criterion
		 */
		public $criteria = array ();
		
		/**
		 *
		 * @return dal_common_criteria
		 */
		protected function addCriterionWithoutValue($condition) {
			$c = new dal_common_criterion ( $condition, null, null,true);
			
			$this->criteria [] = $c;
			
			return $this;
		}
		
		/**
		 * 	 
		 * @return dal_common_criteria
		 */
		protected function addCriterion($condition, $value=null, $secondValue=null, $property=null) {
			if($property ==null && $secondValue!=null){
				$property=$secondValue;
				$secondValue=null;
			}
						
			$c = new dal_common_criterion ( $condition, $value, $secondValue ,false);
			
			$this->criteria [] = $c;
		  
			return $this;
		}
	}
	
	/**
	 * 分页数据
	 */
	abstract class dal_common_pagination {
		/**
		 * 总记录数
		 * 
		 * @var integer
		 */
		public $total = 0;
		 
		
		/**
		 * 偏移量
		 * 
		 * @var integer
		 */
		public $offset = 0;
		
		/**
		 * 需获取记录的条数(等于页面大小)
		 * 
		 * @var integer
		 */
		public $limit = 20;
		
		/**
		 * 页面号(0表示第1页)
		 * 
		 * @var integer
		 */
		public $pageIndex = 0;
		
		/**
		 * 总页数
		 *
		 * @return integer
		 */
		public $totalPages=0;
		
		 
	}
	
	/**
	 * 数据分页
	 */
	class dal_common_rowbounds {
		/**
		 * 偏移量,从0开始
		 * 
		 * @var integer
		 */
		public $offset = 0;
		
		/**
		 * 限制记录数
		 * 
		 * @var integer
		 */
		public $limit = 1000;
		
		/**
		 * 
		 * @param integer $limit   记录条数
		 * @param integer $offset  记录偏移量(从0开始)
		 */
		function __construct($limit=1000,$offset=0){
			$this->limit=$limit;
			$this->offset=$offset;
			
		}
	}
}
?>