package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

public class GroupModificationUpdateTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        applicationManager.goTo().groupPage();
        if (applicationManager.group().list().size() == 0) {
            applicationManager.group()
                    .create(new GroupData().withName("qaNAme").withHeader("QaHeader").withFooter("qaFooter"));
        }
    }

    @Test
    public void testGroupUpdate () {
        Groups before = applicationManager.group().all();
        GroupData modifiedGroup = before.iterator().next();
        // int index = before.size() - 1;
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("qaNAme")
                .withHeader("QaHeader").withFooter("qaFooter");
        // int before  = applicationManager.getGroupHelper().getGroupCount();
        applicationManager.group().modify(group);
        Groups after = applicationManager.group().all();
        // int after  = applicationManager.getGroupHelper().getGroupCount();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);
    /*    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);*/
        Assert.assertEquals(before, after);
        MatcherAssert.assertThat(after, CoreMatchers.equalTo((before.without(modifiedGroup).withAdded(group))));
        //  Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
