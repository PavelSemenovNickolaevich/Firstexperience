package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class DeleteGroupTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        applicationManager.getNavigationHelper().goToGroupPage();
        if (!applicationManager.getGroupHelper().isThereGroup()) {
            applicationManager.getGroupHelper()
                    .CreateGroup(new GroupData("Qa", "QAheader", "QAfooter"));
        }
    }

    @Test
    public void deleteGroup() {
        List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
      //  int before  = applicationManager.getGroupHelper().getGroupCount();
        applicationManager.getGroupHelper().selectGroup(before.size()- 1);
        applicationManager.getGroupHelper().deleteGroup();
        applicationManager.getGroupHelper().returnToGroupPage();
        List<GroupData> after = applicationManager.getGroupHelper().getGroupList();
       // int after  = applicationManager.getGroupHelper().getGroupCount();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before , after);
    }
}
