package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationUpdateTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("egfggege4").withFooter("etete"));
        }
//        app.goTo().groupPage();
//        if (app.group().all().size() == 0) {
//            app.group().create(new GroupData().withName("test1").withHeader("egfggege4").withFooter("etete"));
//        }
    }

    @Test
    public void testGroupUpdate() {
        //   Groups before = app.group().all();
        app.goTo().groupPage();
        Groups before = app.db().groups();
        GroupData modifyGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifyGroup.getId())
                .withName("test1").withHeader("test2").withFooter("test3");
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        //  Groups after = app.group().all();
        Groups after = app.db().groups();
        before.remove(modifyGroup);
        before.add(group);
//        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byId);
//        after.sort(byId);
        // Assert.assertEquals( new HashSet<>(before),new HashSet<>(after));
        Assert.assertEquals(before, after);
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifyGroup).withAdded(group)));
        System.out.println(after.toString());
    }

}
