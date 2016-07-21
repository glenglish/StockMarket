package dzone.challenge.stocktrader;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by gregory.english on 7/20/16.
 */
public class StockTrader {
    static class BuySell {
        int tick = 0;
        BigDecimal buy = null;
        BigDecimal sell = null;

        public BuySell() {
        }

        BigDecimal gain(BigDecimal sell) {
            if(sell == null) {
                return BigDecimal.ZERO;
            }

            return sell.subtract(buy);
        }

        BigDecimal gain() {
            if(sell == null) {
                return BigDecimal.ZERO;
            }

            return sell.subtract(buy);
        }
    }

    public static void main(String [] args ) {
        String strPrices = "9.20 8.03 10.02 8.08 8.14 8.10 8.31 8.28 8.35 8.34 8.39 8.45 8.38 8.38 8.32 8.36 8.28 8.28 8.38 8.48 8.49 8.54 8.73 8.72 8.76 8.74 8.87 8.82 8.81 8.82 8.85 8.85 8.86 8.63 8.70 8.68 8.72 8.77 8.69 8.65 8.70 8.98 8.98 8.87 8.71 9.17 9.34 9.28 8.98 9.02 9.16 9.15 9.07 9.14 9.13 9.10 9.16 9.06 9.10 9.15 9.11 8.72 8.86 8.83 8.70 8.69 8.73 8.73 8.67 8.70 8.69 8.81 8.82 8.83 8.91 8.80 8.97 8.86 8.81 8.87 8.82 8.78 8.82 8.77 8.54 8.32 8.33 8.32 8.51 8.53 8.52 8.41 8.55 8.31 8.38 8.34 8.34 8.19 8.17 8.16";
        String[] arrPrices = strPrices.split(" ");
        ArrayList<BuySell> trades = new ArrayList<BuySell>();

        for(int i = 0; i < arrPrices.length; i++) {
            BuySell newBS = new BuySell();
            newBS.tick = i;
            newBS.buy = new BigDecimal(arrPrices[i]);

            for(int j = 0; j < (i - 1); j++) {
                BuySell bs = trades.get(j);

                if(bs.gain().compareTo(bs.gain(newBS.buy)) < 0) {
                    bs.sell = newBS.buy;
                }
            }

            trades.add(newBS);
        }

        BuySell bestGain = trades.get(0);
        for(int i = 1; i < trades.size(); i++) {
            if(bestGain.gain().compareTo(trades.get(i).gain()) < 0) {
                bestGain = trades.get(i);
            }
        }

        System.out.println(bestGain.buy.toString() + " " + bestGain.sell.toString());
    }
}
