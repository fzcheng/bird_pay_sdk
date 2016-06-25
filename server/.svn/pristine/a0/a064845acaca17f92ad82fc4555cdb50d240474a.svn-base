package jeecg.ext.tools;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
 
import org.apache.log4j.Logger;
 
public class GenerateSequenceUtil {
 
    /** .log */
    private static final Logger logger = Logger.getLogger(GenerateSequenceUtil.class);
 
    /** The FieldPosition. */
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);
 
    /** This Format for format the data to special format. */
    private final static Format dateFormat = new SimpleDateFormat("MMddHHmmssS");
 
    /** This Format for format the number to special format. */
    private final static NumberFormat numberFormat = new DecimalFormat("0000");
    private final static NumberFormat numberFormat_ = new DecimalFormat("0");
 
    /** This int is the sequence number ,the default value is 0. */
    private static int seq = 0;
 
    private static final int MAX = 9999;
    private static final int MAX_ = 9;
 
    /**
     * 时间格式生成序列
     * @return String
     */
    public static synchronized String generateSequenceNo() {
 
        Calendar rightNow = Calendar.getInstance();
 
        StringBuffer sb = new StringBuffer();
 
        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);
 
        numberFormat.format(seq, sb, HELPER_POSITION);
 
        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        }
 
        logger.info("THE SQUENCE IS :" + sb.toString());
 
        return sb.toString();
    }
    
    /**
     * 时间格式生成序列
     * @return String
     */
    public static synchronized int generateSequenceNoInt() {
 
        long time = System.currentTimeMillis()/1000;
        StringBuffer sb = new StringBuffer();
        System.out.println("THE TIME IS :"+time);
        sb.append(time);
        numberFormat_.format(seq, sb, HELPER_POSITION);
        System.out.println("THE SQUENCE2 IS :"+sb.toString());
        if (seq == MAX_) {
            seq = 0;
        } else {
            seq++;
        }
 
        logger.info("THE SQUENCE IS :" + sb.toString());
 
        return Integer.parseInt(sb.toString());
    }
}