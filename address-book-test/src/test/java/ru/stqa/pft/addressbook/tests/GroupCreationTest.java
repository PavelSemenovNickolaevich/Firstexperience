package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() {
        List<Object[]>list = new ArrayList<Object[]>();
        list.add(new Object[] {"test1", "header 1", "footer 1"});
        list.add(new Object[] {"test2", "header 2", "footer 2"});
        list.add(new Object[] {"test3", "header 3", "footer 3"});
        return list.iterator();
    }

    @Test (dataProvider = "validGroups")
    public void testGroupCreation (String name, String header, String footer) throws Exception {
        applicationManager.goTo().groupPage();
        GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
        Groups before = applicationManager.group().all();
        //  int before  = applicationManager.getGroupHelper().getGroupCount();
        applicationManager.group().create(group);
        Groups after = applicationManager.group().all();
        //  int after  = applicationManager.getGroupHelper().getGroupCount();
        // Assert.assertEquals(after.size(), before.size() + 1);
        assertThat(after.size(), equalTo(before.size() + 1));
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
        //  group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
        // before.add(group);
        // Assert.assertEquals(before, after);
        //    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        applicationManager.group().logoutGroup();
    }

}