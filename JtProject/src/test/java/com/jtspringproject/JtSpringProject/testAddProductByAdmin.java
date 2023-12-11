/*package com.jtspringproject.JtSpringProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
//import java.util.concurrent.TimeUnit;

public class testAddProductByAdmin {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\driver\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/admin/login");
        //properties tag replaced and dependencies added as jar


        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123");

        WebElement login = driver.findElement(By.id("submitBtn"));
        login.click();

        // Explicitly wait for the "Manage Product" button to be clickable
        // driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        // Add implicit wait
        // Add implicit wait
       //  driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);

        //added id for manageProduct
        WebElement manageProductBtn = driver.findElement(By.id("manageProduct"));
        manageProductBtn.click();

        WebElement addProductBtn = driver.findElement(By.linkText("Add Product"));
        addProductBtn.click();

        //set name
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys("Orange");
        //set categorySelector
        Select category = new Select(driver.findElement(By.id("categorySelector")));
        category.selectByValue("1");
        //set priceInput
        WebElement priceField = driver.findElement(By.id("priceInput"));
        priceField.sendKeys("5");
        //set id
        WebElement weightField = driver.findElement(By.id("weightInput"));
        weightField.sendKeys("55");
        //set id
        WebElement quantityField = driver.findElement(By.id("quantityInput"));
        quantityField.sendKeys("80");
        //set id
        WebElement descriptionField = driver.findElement(By.id("descriptionInput"));
        descriptionField.sendKeys("Fresh and Sweet");
        //set id
        WebElement imageField = driver.findElement(By.id("imageInput"));
        imageField.sendKeys("https://freepngimg.com/thumb/fruit/174895-picture-organic-fruits-free-png-hq.png");
        //set id
        WebElement submitBtn = driver.findElement(By.id("submitProductBtn"));
        submitBtn.click();
    }
}*/
