package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationUpdateTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        applicationManager.getNavigationHelper().goToGroupPage();
        if (!applicationManager.getGroupHelper().isThereGroup()) {
            applicationManager.getGroupHelper()
                    .CreateGroup(new GroupData("Qa", "QAheader", "QAfooter"));
        }
    }

    @Test
    public void testGroupUpdate() {
        List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
       // int before  = applicationManager.getGroupHelper().getGroupCount();
        applicationManager.getGroupHelper().selectGroup(before.size() - 1);
        applicationManager.getGroupHelper().initGroupModification();
        GroupData group = new GroupData(before.get(before.size() - 1).getId(),"Terminator", "QAheader", "QAfooter");
        applicationManager.getGroupHelper().fillGroupForm(group);
        applicationManager.getGroupHelper().submitGroupModification();
        applicationManager.getGroupHelper().returnToGroupPage();
        List<GroupData> after = applicationManager.getGroupHelper().getGroupList();
       // int after  = applicationManager.getGroupHelper().getGroupCount();
        Assert.assertEquals(after.size(), before.size() );

        before.remove(before.size() - 1);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
      //  Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
