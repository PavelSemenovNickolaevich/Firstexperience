package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class DeleteGroupTest extends TestBase{

    @Test
    public void deleteGroup() {
        applicationManager.getNavigationHelper().goToGroupPage();
        applicationManager.getGroupHelper().selectGroup();
        applicationManager.getGroupHelper().deleteGroup();
        applicationManager.getGroupHelper().returnToGroupPage();
    }
}
