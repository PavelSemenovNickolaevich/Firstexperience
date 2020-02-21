package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class DeleteGroupTest extends TestBase{

    @Test
    public void deleteGroup() {
        applicationManager.getNavigationHelper().goToGroupPage();
        if (!applicationManager.getGroupHelper().isThereGroup()) {
            applicationManager.getGroupHelper().CreateGroup(new GroupData("Qa", "QAheader", "QAfooter"));
        }
        applicationManager.getGroupHelper().selectGroup();
        applicationManager.getGroupHelper().deleteGroup();
        applicationManager.getGroupHelper().returnToGroupPage();
    }
}
