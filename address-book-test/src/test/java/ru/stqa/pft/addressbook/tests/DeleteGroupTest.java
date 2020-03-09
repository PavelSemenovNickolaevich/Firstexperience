package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class DeleteGroupTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        applicationManager.goTo().groupPage();
        if (applicationManager.group().list().size() == 0) {
            applicationManager.group()
                    .create(new GroupData().withName("qaNAme").withHeader("QaHeader").withFooter("qaFooter"));
        }
    }

    @Test
    public void deleteGroup() {
        List<GroupData> before = applicationManager.group().list();
        int index = before.size() - 1;
      //  int before  = applicationManager.getGroupHelper().getGroupCount();
        applicationManager.group().delete(index);
        List<GroupData> after = applicationManager.group().list();
       // int after  = applicationManager.getGroupHelper().getGroupCount();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before , after);
    }

    private void delete (int index) {
        applicationManager.group().selectGroup(index);
        applicationManager.group().deleteGroup();
        applicationManager.group().returnToGroupPage();
    }
}
