package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationUpdateTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        applicationManager.goTo().groupPage();
        if (applicationManager.group().list().size() == 0) {
            applicationManager.group()
                    .create(new GroupData().withName("qaNAme").withHeader("QaHeader").withFooter("qaFooter"));
        }
    }

    @Test
    public void testGroupUpdate() {
        List<GroupData> before = applicationManager.group().list();
        int index = before.size() - 1;
        GroupData group = new GroupData().withId(before.get(index).getId()).withName("qaNAme")
                .withHeader("QaHeader").withFooter("qaFooter");
       // int before  = applicationManager.getGroupHelper().getGroupCount();
        applicationManager.group().modify(index, group);
        List<GroupData> after = applicationManager.group().list();
       // int after  = applicationManager.getGroupHelper().getGroupCount();
        Assert.assertEquals(after.size(), before.size() );

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
      //  Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
