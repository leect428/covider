package usc.covider.cs310;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import androidx.annotation.NonNull;
import usc.covider.cs310.util.DateUtil;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DateUtilTest {
    @Test
    public void testDateUtil() {
        String dateString = DateUtil.getToday();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Boolean parsed = false;
        try {
            Date date = formatter.parse(dateString);
            assertEquals(date.getMinutes(), 0);
            assertEquals(date.getHours(), 0);
            assertEquals(date.getSeconds(), 0);
            parsed = true;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert(parsed);
    }
}