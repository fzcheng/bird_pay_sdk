select 
*, ch.cgr_sql as cgreport_sql
from jform_cgreport_head ch
where ch.code = :id