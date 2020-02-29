package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        applicationManager.getNavigationHelper().goToGroupPage();
        List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
      //  int before  = applicationManager.getGroupHelper().getGroupCount();
        GroupData group = new GroupData("Qa", "QAheader", "QAfooter");
        applicationManager.getGroupHelper().initGroupCreation();
        applicationManager.getGroupHelper().fillGroupForm(group);
        applicationManager.getGroupHelper().submitGroupCreation();
        applicationManager.getGroupHelper().returnToGroupPage();
        List<GroupData> after = applicationManager.getGroupHelper().getGroupList();
      //  int after  = applicationManager.getGroupHelper().getGroupCount();
        Assert.assertEquals(after.size(), before.size() +1);
   /*     int max = 0;
        for (GroupData g : after) {
            if (g.getId() > max) {
                max = g.getId();
            }
        }

    */
      //  group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    //    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        applicationManager.getGroupHelper().logoutGroup();
    }
}