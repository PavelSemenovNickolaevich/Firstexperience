package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteGroupTest extends TestBase{

    @Test
    public void deleteGroup() {
        applicationManager.getNavigationHelper().goToGroupPage();
        applicationManager.getGroupHelper().selectGroup();
        applicationManager.getGroupHelper().deleteGroup();
        applicationManager.getGroupHelper().returnToGroupPage();
    }
}
