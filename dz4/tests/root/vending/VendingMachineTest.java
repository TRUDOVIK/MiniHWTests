package root.vending;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VendingMachineTest {

    private static VendingMachine vendingMachine;
    private static final long id = 117345294655382L;

    @BeforeEach
    public void initTest() {
        vendingMachine = new VendingMachine();
    }

    @AfterEach
    public void endTest() {
        vendingMachine = null;
    }

    @Test
    void getNumberOfProduct1Default() {
        Assertions.assertEquals(0, vendingMachine.getNumberOfProduct1());
    }

    @Test
    void getNumberOfProduct1AfterFill() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        Assertions.assertEquals(30, vendingMachine.getNumberOfProduct1());
    }

    @Test
    void getNumberOfProduct1AfterGive() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.exitAdminMode();

        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        vendingMachine.putCoin2();

        vendingMachine.giveProduct1(1);
        Assertions.assertEquals(29, vendingMachine.getNumberOfProduct1());
    }

    @Test
    void getNumberOfProduct2Default() {
        Assertions.assertEquals(0, vendingMachine.getNumberOfProduct2());
    }

    @Test
    void getNumberOfProduct2AfterFill() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        Assertions.assertEquals(40, vendingMachine.getNumberOfProduct2());
    }

    @Test
    void getNumberOfProduct2AfterGive() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.exitAdminMode();

        for (int i = 0; i < 50; i++) {
            vendingMachine.putCoin1();
            vendingMachine.putCoin2();
        }

        vendingMachine.giveProduct2(1);
        Assertions.assertEquals(39, vendingMachine.getNumberOfProduct2());
    }

    @Test
    void getCurrentBalanceDefault() {
        Assertions.assertEquals(0, vendingMachine.getCurrentBalance());
    }

    @Test
    void getCurrentBalanceAfterEdit() {
        vendingMachine.exitAdminMode();
        vendingMachine.putCoin1();
        vendingMachine.putCoin2();
        Assertions.assertEquals(3, vendingMachine.getCurrentBalance());
    }

    @Test
    void getCurrentModeDefault() {
        Assertions.assertEquals(VendingMachine.Mode.OPERATION, vendingMachine.getCurrentMode());
    }

    @Test
    void getCurrentModeAdministering() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Mode.ADMINISTERING, vendingMachine.getCurrentMode());
    }

    @Test
    void getCurrentModeOperation() {
        vendingMachine.exitAdminMode();
        Assertions.assertEquals(VendingMachine.Mode.OPERATION, vendingMachine.getCurrentMode());
    }

    @Test
    void getCurrentSumDefault() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(0, vendingMachine.getCurrentSum());
    }

    @Test
    void getCurrentSumWrongMode() {
        vendingMachine.exitAdminMode();
        vendingMachine.putCoin1();
        Assertions.assertEquals(0, vendingMachine.getCurrentSum());
    }

    @Test
    void getCurrentSumAfterPuttingCoins() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillCoins(1, 2);
        Assertions.assertEquals(5, vendingMachine.getCurrentSum());
    }

    @Test
    void getCoins1Default() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(0, vendingMachine.getCoins1());
    }

    @Test
    void getCoins1AfterAdd() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillCoins(1, 2);
        Assertions.assertEquals(1, vendingMachine.getCoins1());
    }

    @Test
    void getCoins1WrongMode() {
        vendingMachine.exitAdminMode();
        vendingMachine.putCoin1();
        vendingMachine.putCoin2();
        Assertions.assertEquals(0, vendingMachine.getCoins1());
    }

    @Test
    void getCoins2Default() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(0, vendingMachine.getCoins2());
    }

    @Test
    void getCoins2AfterAdd() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillCoins(1, 1);
        Assertions.assertEquals(1, vendingMachine.getCoins2());
    }

    @Test
    void getCoins2WrongMode() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillCoins(1, 2);
        vendingMachine.exitAdminMode();
        Assertions.assertEquals(0, vendingMachine.getCoins2());
    }

    @Test
    void getPrice1Default() {
        Assertions.assertEquals(8, vendingMachine.getPrice1());
    }

    @Test
    void getPrice1AfterEditing() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.setPrices(100, 150);
        Assertions.assertEquals(100, vendingMachine.getPrice1());
    }

    @Test
    void getPrice2Default() {
        Assertions.assertEquals(5, vendingMachine.getPrice2());
    }

    @Test
    void getPrice2AfterEditing() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.setPrices(100, 150);
        Assertions.assertEquals(150, vendingMachine.getPrice2());
    }

    @Test
    void fillProductsWrongMode() {
        vendingMachine.exitAdminMode();
        Assertions.assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, vendingMachine.fillProducts());
    }

    @Test
    void fillProductsTrueMode() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.fillProducts());
    }



    @Test
    void fillCoinsWrongMode() {
        vendingMachine.exitAdminMode();
        Assertions.assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, vendingMachine.fillCoins(5, 5));
    }

    @Test
    void fillCoinsTrueMode() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.fillCoins(5, 5));
    }

    @Test
    void fillCoinsLessZeroC1() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Response.INVALID_PARAM, vendingMachine.fillCoins(0, 5));
    }

    @Test
    void fillCoinsLessZeroC2() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Response.INVALID_PARAM, vendingMachine.fillCoins(5, 0));
    }

    @Test
    void fillCoinsOverMaxC1() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Response.INVALID_PARAM, vendingMachine.fillCoins(51, 5));
    }

    @Test
    void fillCoinsMaxC1() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.fillCoins(50, 5));
    }

    @Test
    void fillCoinsMaxC2() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.fillCoins(5, 50));
    }

    @Test
    void fillCoinsOverMaxC2() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Response.INVALID_PARAM, vendingMachine.fillCoins(5, 51));
    }

    @Test
    void fillCoinsEditingCheckC1() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillCoins(3, 6);
        Assertions.assertEquals(3, vendingMachine.getCoins1());
    }

    @Test
    void fillCoinsEditingCheckC2() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillCoins(3, 6);
        Assertions.assertEquals(6, vendingMachine.getCoins2());
    }

    @Test
    void enterAdminModeAllTrue() {
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.enterAdminMode(id));
    }



    @Test
    void enterAdminModeWrongCode() {
        Assertions.assertEquals(VendingMachine.Response.INVALID_PARAM, vendingMachine.enterAdminMode(0L));
    }

    @Test
    void enterAdminModeButMoneyNotEmpty() {
        vendingMachine.exitAdminMode();
        vendingMachine.putCoin1();
        Assertions.assertEquals(VendingMachine.Response.CANNOT_PERFORM, vendingMachine.enterAdminMode(id));
    }

    @Test
    void exitAdminMode() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.exitAdminMode();
        Assertions.assertEquals(VendingMachine.Mode.OPERATION, vendingMachine.getCurrentMode());
    }

    @Test
    void setPricesWrongMode() {
        vendingMachine.exitAdminMode();
        Assertions.assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, vendingMachine.setPrices(5, 5));
    }

    @Test
    void setPricesLessZeroP1() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Response.INVALID_PARAM, vendingMachine.setPrices(0, 4));
    }

    @Test
    void setPricesLessZeroP2() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Response.INVALID_PARAM, vendingMachine.setPrices(4, 0));
    }

    @Test
    void setPricesAllTrue() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.setPrices(4, 4));
    }



    @Test
    void putCoin1WrongMode() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, vendingMachine.putCoin1());
    }

    @Test
    void putCoin1Overloaded() {
        vendingMachine.exitAdminMode();
        for (int i = 0; i < 50; i++) {
            vendingMachine.putCoin1();
        }
        Assertions.assertEquals(VendingMachine.Response.CANNOT_PERFORM, vendingMachine.putCoin1());
    }

    @Test
    void putCoin1AllTrue() {
        vendingMachine.exitAdminMode();
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.putCoin1());
    }



    @Test
    void putCoin2WrongMode() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, vendingMachine.putCoin2());
    }

    @Test
    void putCoin2Overloaded() {
        vendingMachine.exitAdminMode();
        for (int i = 0; i < 50; i++) {
            vendingMachine.putCoin2();
        }
        Assertions.assertEquals(VendingMachine.Response.CANNOT_PERFORM, vendingMachine.putCoin2());
    }

    @Test
    void putCoin2AllTrue() {
        vendingMachine.exitAdminMode();
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.putCoin2());
    }



    @Test
    void returnMoneyWrongMode() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, vendingMachine.returnMoney());
    }

    @Test
    void returnMoneyZeroBalance() {
        vendingMachine.exitAdminMode();
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.returnMoney());
        Assertions.assertEquals(0, vendingMachine.getCurrentBalance());
    }



    @Test
    void returnMoneyOrderOfMoneyReturnCheckOddBalanceNotEnoughCoin2() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.exitAdminMode();

        for (int i = 0; i < 11; i++) {
            vendingMachine.putCoin1();
        }

        vendingMachine.putCoin2();
        // 11*1+1*2=13

        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.returnMoney());
        Assertions.assertEquals(0, vendingMachine.getCurrentBalance());
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(0, vendingMachine.getCoins2());
        Assertions.assertEquals(0, vendingMachine.getCoins1());
    }

    @Test
    void returnMoneyOrderOfMoneyReturnCheckEvenBalance() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.exitAdminMode();

        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        vendingMachine.putCoin2();

        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.returnMoney());
        Assertions.assertEquals(0, vendingMachine.getCurrentBalance());
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(0, vendingMachine.getCoins2());
        Assertions.assertEquals(0, vendingMachine.getCoins1());
    }

    @Test
    void returnMoneyOrderOfMoneyReturnCheckOddBalanceEnoughCoin2() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.fillCoins(10, 10);
        vendingMachine.exitAdminMode();

        vendingMachine.putCoin1();
        vendingMachine.putCoin1();
        vendingMachine.putCoin1();

        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        // 3*1+5*2=13
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.returnMoney());
        Assertions.assertEquals(0, vendingMachine.getCurrentBalance());
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(9, vendingMachine.getCoins2());
        Assertions.assertEquals(12, vendingMachine.getCoins1());
    }



    @Test
    void giveProduct1WrongMode() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, vendingMachine.giveProduct1(1));
    }

    @Test
    void giveProduct1LessZero() {
        vendingMachine.exitAdminMode();
        Assertions.assertEquals(VendingMachine.Response.INVALID_PARAM, vendingMachine.giveProduct1(0));
    }

    @Test
    void giveProduct1OverMax() {
        vendingMachine.exitAdminMode();
        Assertions.assertEquals(VendingMachine.Response.INVALID_PARAM, vendingMachine.giveProduct1(31));
    }

    @Test
    void giveProduct1Max() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.setPrices(1, 1);
        vendingMachine.exitAdminMode();
        for (int i = 0; i < 50; i++) {
            vendingMachine.putCoin1();
            vendingMachine.putCoin2();
        }
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.giveProduct1(30));
    }

    @Test
    void giveProduct1NoMoney() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.setPrices(1, 1);
        vendingMachine.exitAdminMode();
        Assertions.assertEquals(VendingMachine.Response.INSUFFICIENT_MONEY, vendingMachine.giveProduct1(1));
    }

    @Test
    void giveProduct1OverCurrentCountProduct() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.setPrices(1, 1);
        vendingMachine.exitAdminMode();
        for (int i = 0; i < 50; i++) {
            vendingMachine.putCoin1();
            vendingMachine.putCoin2();
        }
        vendingMachine.giveProduct1(4);
        Assertions.assertEquals(VendingMachine.Response.INSUFFICIENT_PRODUCT, vendingMachine.giveProduct1(28));
    }

    @Test
    void giveProduct1OrderOfMoneyReturnCheckOddBalanceNotEnoughCoin2() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.exitAdminMode();

        for (int i = 0; i < 11; i++) {
            vendingMachine.putCoin1();
        }

        vendingMachine.putCoin2();
        // 11*1+1*2=13

        // 13-8=5
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.giveProduct1(1));
        Assertions.assertEquals(0, vendingMachine.getCurrentBalance());
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(0, vendingMachine.getCoins2());
        Assertions.assertEquals(8, vendingMachine.getCoins1());
    }

    @Test
    void giveMoreThanOneProduct1() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.setPrices(1, 1);
        vendingMachine.exitAdminMode();
        for (int i = 0; i < 50; i++) {
            vendingMachine.putCoin1();
            vendingMachine.putCoin2();
        }
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.giveProduct1(2));
        Assertions.assertEquals(0, vendingMachine.getCurrentBalance());
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(28, vendingMachine.getNumberOfProduct1());
    }

    @Test
    void giveProduct1NoChange() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.setPrices(1, 1);
        vendingMachine.exitAdminMode();
        for (int i = 0; i < 50; i++) {
            vendingMachine.putCoin2();
        }
        Assertions.assertEquals(VendingMachine.Response.UNSUITABLE_CHANGE, vendingMachine.giveProduct1(1));
    }

    @Test
    void giveProduct1OrderOfMoneyReturnCheckEvenBalance() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.exitAdminMode();

        vendingMachine.putCoin1();
        vendingMachine.putCoin1();
        vendingMachine.putCoin1();
        vendingMachine.putCoin1();
        vendingMachine.putCoin1();
        vendingMachine.putCoin1();

        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        // 6*1+3*2=12
        // 12-8=4
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.giveProduct1(1));
        Assertions.assertEquals(0, vendingMachine.getCurrentBalance());
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(1, vendingMachine.getCoins2());
        Assertions.assertEquals(6, vendingMachine.getCoins1());
    }

    @Test
    void giveProduct1OrderOfMoneyReturnCheckOddBalanceEnoughCoin2() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.exitAdminMode();

        vendingMachine.putCoin1();
        vendingMachine.putCoin1();
        vendingMachine.putCoin1();

        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        // 3*1+5*2=13
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.giveProduct1(1));
        Assertions.assertEquals(0, vendingMachine.getCurrentBalance());
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(3, vendingMachine.getCoins2());
        Assertions.assertEquals(2, vendingMachine.getCoins1());
        Assertions.assertEquals(29, vendingMachine.getNumberOfProduct1());
    }



    @Test
    void giveProduct2WrongMode() {
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, vendingMachine.giveProduct2(1));
    }

    @Test
    void giveProduct2LessZero() {
        vendingMachine.exitAdminMode();
        Assertions.assertEquals(VendingMachine.Response.INVALID_PARAM, vendingMachine.giveProduct2(0));
    }

    @Test
    void giveProduct2OverMax() {
        vendingMachine.exitAdminMode();
        Assertions.assertEquals(VendingMachine.Response.INVALID_PARAM, vendingMachine.giveProduct2(41));
    }

    @Test
    void giveProduct2Max() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.setPrices(1, 1);
        vendingMachine.exitAdminMode();
        for (int i = 0; i < 50; i++) {
            vendingMachine.putCoin1();
            vendingMachine.putCoin2();
        }
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.giveProduct2(40));
    }

    @Test
    void giveProduct2NoMoney() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.setPrices(1, 1);
        vendingMachine.exitAdminMode();
        Assertions.assertEquals(VendingMachine.Response.INSUFFICIENT_MONEY, vendingMachine.giveProduct2(1));
    }

    @Test
    void giveProduct2OverCurrentCountProduct() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.setPrices(1, 1);
        vendingMachine.exitAdminMode();
        for (int i = 0; i < 50; i++) {
            vendingMachine.putCoin1();
            vendingMachine.putCoin2();
        }
        vendingMachine.giveProduct2(4);
        Assertions.assertEquals(VendingMachine.Response.INSUFFICIENT_PRODUCT, vendingMachine.giveProduct2(38));
    }

    @Test
    void giveProduct2OrderOfMoneyReturnCheckOddBalanceNotEnoughCoin2() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.exitAdminMode();

        for (int i = 0; i < 10; i++) {
            vendingMachine.putCoin1();
        }

        vendingMachine.putCoin2();
        // 10*1+1*2=12

        // 12-5=7
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.giveProduct2(1));
        Assertions.assertEquals(0, vendingMachine.getCurrentBalance());
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(0, vendingMachine.getCoins2());
        Assertions.assertEquals(5, vendingMachine.getCoins1());
    }

    @Test
    void giveMoreThanOneProduct2() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.setPrices(1, 1);
        vendingMachine.exitAdminMode();
        for (int i = 0; i < 50; i++) {
            vendingMachine.putCoin1();
            vendingMachine.putCoin2();
        }
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.giveProduct2(2));
        Assertions.assertEquals(0, vendingMachine.getCurrentBalance());
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(38, vendingMachine.getNumberOfProduct2());
    }

    @Test
    void giveProduct2OrderOfMoneyReturnCheckEvenBalance() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.exitAdminMode();

        vendingMachine.putCoin1();
        vendingMachine.putCoin1();
        vendingMachine.putCoin1();
        vendingMachine.putCoin1();
        vendingMachine.putCoin1();

        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        // 5*1+3*2=11
        // 11-5=6
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.giveProduct2(1));
        Assertions.assertEquals(0, vendingMachine.getCurrentBalance());
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(0, vendingMachine.getCoins2());
        Assertions.assertEquals(5, vendingMachine.getCoins1());
        Assertions.assertEquals(39, vendingMachine.getNumberOfProduct2());
    }

    @Test
    void giveProduct2OrderOfMoneyReturnCheckOddBalanceEnoughCoin2() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.exitAdminMode();

        vendingMachine.putCoin1();
        vendingMachine.putCoin1();

        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        vendingMachine.putCoin2();
        // 2*1+5*2=12
        // 12-5=7
        Assertions.assertEquals(VendingMachine.Response.OK, vendingMachine.giveProduct2(1));
        Assertions.assertEquals(0, vendingMachine.getCurrentBalance());
        vendingMachine.enterAdminMode(id);
        Assertions.assertEquals(2, vendingMachine.getCoins2());
        Assertions.assertEquals(1, vendingMachine.getCoins1());
        Assertions.assertEquals(39, vendingMachine.getNumberOfProduct2());
    }

    @Test
    void giveProduct2OddBalanceButNoCoin1() {
        vendingMachine.enterAdminMode(id);
        vendingMachine.fillProducts();
        vendingMachine.exitAdminMode();

        for (int i = 0; i < 9; i++) {
            vendingMachine.putCoin2();
        }
        // 9*2=18
        // 18-5=13
        Assertions.assertEquals(VendingMachine.Response.UNSUITABLE_CHANGE, vendingMachine.giveProduct2(1));
    }
}