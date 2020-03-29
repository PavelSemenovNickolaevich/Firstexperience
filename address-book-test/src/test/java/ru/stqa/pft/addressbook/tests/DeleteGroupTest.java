package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class DeleteGroupTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions () {
        applicationManager.goTo().groupPage();
        if (applicationManager.group().list().size() == 0) {
            applicationManager.group()
                    .create(new GroupData().withName("qaNAme").withHeader("QaHeader").withFooter("qaFooter"));
        }
    }

    @Test
    public void deleteGroup () {
        Groups before = applicationManager.db().groups();
        GroupData deleteGroup = before.iterator().next();
        //  int before  = applicationManager.getGroupHelper().getGroupCount();
        applicationManager.group().delete(deleteGroup);
        Groups after = applicationManager.db().groups();
        // int after  = applicationManager.getGroupHelper().getGroupCount();
        assertEquals(after.size(), before.size() - 1);

        //   before.remove(deleteGroup);
        assertThat(after, equalTo(before.without(deleteGroup)));
        //    Assert.assertEquals(before , after);
    }

}
