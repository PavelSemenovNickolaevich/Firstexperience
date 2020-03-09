package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        applicationManager.goTo().groupPage();
        Set<GroupData> before = applicationManager.group().all();
      //  int before  = applicationManager.getGroupHelper().getGroupCount();
        GroupData group = new GroupData().withName("qa2").withHeader("test").withFooter("testqa");
        applicationManager.group().create(group);
        Set<GroupData> after = applicationManager.group().all();
      //  int after  = applicationManager.getGroupHelper().getGroupCount();
        Assert.assertEquals(after.size(), before.size() + 1);
   /*     int max = 0;
        for (GroupData g : after) {
            if (g.getId() > max) {
                max = g.getId();
            }
        }

    */
      //  group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
       /* Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);*/
        group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);
    //    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        applicationManager.group().logoutGroup();
    }

}