<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.cheyooh.service.dal.db.#{db}.dao.#{dbUpper}DDQMapper">
	<!-- 避免Mybatis载入时出现空语句异常   --> 
	<select id="selectTest" resultType="org.apache.commons.collections.map.CaseInsensitiveMap" parameterType="org.apache.commons.collections.map.CaseInsensitiveMap">       
	   <![CDATA[	   
	        SELECT 1
	    ]]>
	</select>
  
</mapper>