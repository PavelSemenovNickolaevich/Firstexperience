package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test2").withHeader("213").withFooter("4r32r432");
        app.group().create(group);
        Groups after = app.group().all();
        //Assert.assertEquals(after.size(), before.size() + 1);
        assertThat(after.size(), equalTo(before.size() + 1));
        //  group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
//        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byId);
//        after.sort(byId);
        //Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
        //  Assert.assertEquals(before, after);
        assertThat(after, equalTo(before.withAdded(group)));
    }

}