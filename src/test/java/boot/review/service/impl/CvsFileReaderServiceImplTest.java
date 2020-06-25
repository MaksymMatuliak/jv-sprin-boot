package boot.review.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class CvsFileReaderServiceImplTest {

    @Test
    public void read() {
        CvsFileReaderServiceImpl cvsFileReaderService= new CvsFileReaderServiceImpl();
        String PATH = "src\\test\\java\\resources\\test";
        List<String> expected = new ArrayList<>();
        expected.add("1,B001E4KFG0,A3SGXH7AUHU8GW,delmartian,1,1,5,1303862400," +
                "Good Quality Dog Food,I have bought several of the Vitality canned dog food " +
                "products and have found them all to be of good quality. The product looks more " +
                "like a stew than a processed meat and it smells better. My Labrador is finicky " +
                "and she appreciates this product better than  most.");
        expected.add("2,B00813GRG4,A1D87F6ZCVE5NK,dll pa,0,0,1,1346976000,Not as Advertised," +
                "\"Product arrived labeled as Jumbo Salted Peanuts...the peanuts were actually" +
                " small sized unsalted. Not sure if this was an error or if the vendor intended " +
                "to represent the product as \"\"Jumbo\"\".\"");
        Assert.assertEquals(expected, cvsFileReaderService.read(PATH));
    }
}
