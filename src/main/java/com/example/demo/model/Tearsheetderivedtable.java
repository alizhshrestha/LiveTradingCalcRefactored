/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alizh
 */
@Entity
@Table(name = "tearsheetderivedtable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tearsheetderivedtable.findAll", query = "SELECT t FROM Tearsheetderivedtable t"),
    @NamedQuery(name = "Tearsheetderivedtable.findByIdtearsheetderivedtable", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.idtearsheetderivedtable = :idtearsheetderivedtable"),
    @NamedQuery(name = "Tearsheetderivedtable.findByTicker", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.ticker = :ticker"),
    @NamedQuery(name = "Tearsheetderivedtable.findByStockName", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.stockName = :stockName"),
    @NamedQuery(name = "Tearsheetderivedtable.findByWeightedAvePrice", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.weightedAvePrice = :weightedAvePrice"),
    @NamedQuery(name = "Tearsheetderivedtable.findByTradingDate", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.tradingDate = :tradingDate"),
    @NamedQuery(name = "Tearsheetderivedtable.findByBetaMonthly", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.betaMonthly = :betaMonthly"),
    @NamedQuery(name = "Tearsheetderivedtable.findBySector", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.sector = :sector"),
    @NamedQuery(name = "Tearsheetderivedtable.findBySharesOutstanding", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.sharesOutstanding = :sharesOutstanding"),
    @NamedQuery(name = "Tearsheetderivedtable.findBySharesFloat", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.sharesFloat = :sharesFloat"),
    @NamedQuery(name = "Tearsheetderivedtable.findByMarketCap", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.marketCap = :marketCap"),
    @NamedQuery(name = "Tearsheetderivedtable.findByDaysTraded", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.daysTraded = :daysTraded"),
    @NamedQuery(name = "Tearsheetderivedtable.findByAverageVolume", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.averageVolume = :averageVolume"),
    @NamedQuery(name = "Tearsheetderivedtable.findByAverageNoofTrades", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.averageNoofTrades = :averageNoofTrades"),
    @NamedQuery(name = "Tearsheetderivedtable.findByAverageSharesPerTrade", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.averageSharesPerTrade = :averageSharesPerTrade"),
    @NamedQuery(name = "Tearsheetderivedtable.findByEps", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.eps = :eps"),
    @NamedQuery(name = "Tearsheetderivedtable.findByPe", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.pe = :pe"),
    @NamedQuery(name = "Tearsheetderivedtable.findByPbv", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.pbv = :pbv"),
    @NamedQuery(name = "Tearsheetderivedtable.findByRoe", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.roe = :roe"),
    @NamedQuery(name = "Tearsheetderivedtable.findByRoa", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.roa = :roa"),
    @NamedQuery(name = "Tearsheetderivedtable.findByRelativeStrength", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.relativeStrength = :relativeStrength"),
    @NamedQuery(name = "Tearsheetderivedtable.findByFiftyDayMAVsTwoHundreadDayMA", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.fiftyDayMAVsTwoHundreadDayMA = :fiftyDayMAVsTwoHundreadDayMA"),
    @NamedQuery(name = "Tearsheetderivedtable.findByPriceVsFiftyTwoWeekHigh", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.priceVsFiftyTwoWeekHigh = :priceVsFiftyTwoWeekHigh"),
    @NamedQuery(name = "Tearsheetderivedtable.findByRsi", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.rsi = :rsi"),
    @NamedQuery(name = "Tearsheetderivedtable.findByMacd", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.macd = :macd"),
    @NamedQuery(name = "Tearsheetderivedtable.findByOneWeekPriceChange", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.oneWeekPriceChange = :oneWeekPriceChange"),
    @NamedQuery(name = "Tearsheetderivedtable.findByFourWeekPriceChange", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.fourWeekPriceChange = :fourWeekPriceChange"),
    @NamedQuery(name = "Tearsheetderivedtable.findByTwelveWeekPriceChange", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.twelveWeekPriceChange = :twelveWeekPriceChange"),
    @NamedQuery(name = "Tearsheetderivedtable.findByYtd", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.ytd = :ytd"),
    @NamedQuery(name = "Tearsheetderivedtable.findByVolume", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.volume = :volume"),
    @NamedQuery(name = "Tearsheetderivedtable.findByOneEightyDayAverage", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.oneEightyDayAverage = :oneEightyDayAverage"),
    @NamedQuery(name = "Tearsheetderivedtable.findByVarMonthlyAtFive", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.varMonthlyAtFive = :varMonthlyAtFive"),
    @NamedQuery(name = "Tearsheetderivedtable.findByVarWeeklyAtFive", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.varWeeklyAtFive = :varWeeklyAtFive"),
    @NamedQuery(name = "Tearsheetderivedtable.findByTotalReturn", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.totalReturn = :totalReturn"),
    @NamedQuery(name = "Tearsheetderivedtable.findByPriceChange", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.priceChange = :priceChange"),
    @NamedQuery(name = "Tearsheetderivedtable.findByProfitabilityChange", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.profitabilityChange = :profitabilityChange"),
    @NamedQuery(name = "Tearsheetderivedtable.findBySentimentChange", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.sentimentChange = :sentimentChange"),
    @NamedQuery(name = "Tearsheetderivedtable.findByDaysHigh", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.daysHigh = :daysHigh"),
    @NamedQuery(name = "Tearsheetderivedtable.findByDaysLow", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.daysLow = :daysLow"),
    @NamedQuery(name = "Tearsheetderivedtable.findByFiftyHigh", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.fiftyHigh = :fiftyHigh"),
    @NamedQuery(name = "Tearsheetderivedtable.findByFiftyLow", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.fiftyLow = :fiftyLow"),
    @NamedQuery(name = "Tearsheetderivedtable.findByBetaWeekly", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.betaWeekly = :betaWeekly"),
    @NamedQuery(name = "Tearsheetderivedtable.findByVarWeeklyAtOne", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.varWeeklyAtOne = :varWeeklyAtOne"),
    @NamedQuery(name = "Tearsheetderivedtable.findByVarMonthlyAtOne", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.varMonthlyAtOne = :varMonthlyAtOne"),
    @NamedQuery(name = "Tearsheetderivedtable.findByDaysHighLowPercentile", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.daysHighLowPercentile = :daysHighLowPercentile"),
    @NamedQuery(name = "Tearsheetderivedtable.findByFiftyTwoHighLowPercentile", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.fiftyTwoHighLowPercentile = :fiftyTwoHighLowPercentile"),
    @NamedQuery(name = "Tearsheetderivedtable.findByLatestClose", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.latestClose = :latestClose"),
    @NamedQuery(name = "Tearsheetderivedtable.findByLatestTradingDate", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.latestTradingDate = :latestTradingDate"),
    @NamedQuery(name = "Tearsheetderivedtable.findByTodaysChange", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.todaysChange = :todaysChange"),
    @NamedQuery(name = "Tearsheetderivedtable.findBySharesTraded", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.sharesTraded = :sharesTraded"),
    @NamedQuery(name = "Tearsheetderivedtable.findByOneYearPriceChange", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.oneYearPriceChange = :oneYearPriceChange"),
    @NamedQuery(name = "Tearsheetderivedtable.findByEpsPercentile", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.epsPercentile = :epsPercentile"),
    @NamedQuery(name = "Tearsheetderivedtable.findByPePercentile", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.pePercentile = :pePercentile"),
    @NamedQuery(name = "Tearsheetderivedtable.findByPbPercentile", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.pbPercentile = :pbPercentile"),
    @NamedQuery(name = "Tearsheetderivedtable.findByRoePercentile", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.roePercentile = :roePercentile"),
    @NamedQuery(name = "Tearsheetderivedtable.findByRoaPercentile", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.roaPercentile = :roaPercentile"),
    @NamedQuery(name = "Tearsheetderivedtable.findByEpsHigh", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.epsHigh = :epsHigh"),
    @NamedQuery(name = "Tearsheetderivedtable.findByPeHigh", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.peHigh = :peHigh"),
    @NamedQuery(name = "Tearsheetderivedtable.findByPbHigh", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.pbHigh = :pbHigh"),
    @NamedQuery(name = "Tearsheetderivedtable.findByRoeHigh", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.roeHigh = :roeHigh"),
    @NamedQuery(name = "Tearsheetderivedtable.findByRoaHigh", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.roaHigh = :roaHigh"),
    @NamedQuery(name = "Tearsheetderivedtable.findByEpsLow", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.epsLow = :epsLow"),
    @NamedQuery(name = "Tearsheetderivedtable.findByPeLow", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.peLow = :peLow"),
    @NamedQuery(name = "Tearsheetderivedtable.findByPbLow", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.pbLow = :pbLow"),
    @NamedQuery(name = "Tearsheetderivedtable.findByRoeLow", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.roeLow = :roeLow"),
    @NamedQuery(name = "Tearsheetderivedtable.findByRoaLow", query = "SELECT t FROM Tearsheetderivedtable t WHERE t.roaLow = :roaLow")})
public class Tearsheetderivedtable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtearsheetderivedtable")
    private Integer idtearsheetderivedtable;
    @Column(name = "TICKER")
    private String ticker;
    @Column(name = "STOCK_NAME")
    private String stockName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "WeightedAvePrice")
    private Double weightedAvePrice;
    @Column(name = "TRADING_DATE")
    @Temporal(TemporalType.DATE)
    private Date tradingDate;
    @Column(name = "BetaMonthly")
    private Double betaMonthly;
    @Column(name = "Sector")
    private String sector;
    @Column(name = "SharesOutstanding")
    private Double sharesOutstanding;
    @Column(name = "SharesFloat")
    private Double sharesFloat;
    @Column(name = "MarketCap")
    private Double marketCap;
    @Column(name = "DaysTraded")
    private Double daysTraded;
    @Column(name = "AverageVolume")
    private Double averageVolume;
    @Column(name = "AverageNoofTrades")
    private Double averageNoofTrades;
    @Column(name = "AverageSharesPerTrade")
    private Double averageSharesPerTrade;
    @Column(name = "EPS")
    private Double eps;
    @Column(name = "PE")
    private Double pe;
    @Column(name = "PBV")
    private Double pbv;
    @Column(name = "ROE")
    private Double roe;
    @Column(name = "ROA")
    private Double roa;
    @Column(name = "RelativeStrength")
    private Double relativeStrength;
    @Column(name = "FiftyDayMAVsTwoHundreadDayMA")
    private Double fiftyDayMAVsTwoHundreadDayMA;
    @Column(name = "PriceVsFiftyTwoWeekHigh")
    private Double priceVsFiftyTwoWeekHigh;
    @Column(name = "RSI")
    private Double rsi;
    @Column(name = "MACD")
    private Double macd;
    @Column(name = "OneWeekPriceChange")
    private Double oneWeekPriceChange;
    @Column(name = "FourWeekPriceChange")
    private Double fourWeekPriceChange;
    @Column(name = "TwelveWeekPriceChange")
    private Double twelveWeekPriceChange;
    @Column(name = "YTD")
    private Double ytd;
    @Column(name = "Volume")
    private Double volume;
    @Column(name = "OneEightyDayAverage")
    private Double oneEightyDayAverage;
    @Column(name = "VarMonthlyAtFive")
    private Double varMonthlyAtFive;
    @Column(name = "VarWeeklyAtFive")
    private Double varWeeklyAtFive;
    @Column(name = "TotalReturn")
    private Double totalReturn;
    @Column(name = "PriceChange")
    private Double priceChange;
    @Column(name = "ProfitabilityChange")
    private Double profitabilityChange;
    @Column(name = "SentimentChange")
    private Double sentimentChange;
    @Column(name = "DaysHigh")
    private Double daysHigh;
    @Column(name = "DaysLow")
    private Double daysLow;
    @Column(name = "FiftyHigh")
    private Double fiftyHigh;
    @Column(name = "FiftyLow")
    private Double fiftyLow;
    @Column(name = "BetaWeekly")
    private Double betaWeekly;
    @Column(name = "VarWeeklyAtOne")
    private Double varWeeklyAtOne;
    @Column(name = "VarMonthlyAtOne")
    private Double varMonthlyAtOne;
    @Column(name = "daysHighLowPercentile")
    private Double daysHighLowPercentile;
    @Column(name = "fiftyTwoHighLowPercentile")
    private Double fiftyTwoHighLowPercentile;
    @Column(name = "latestClose")
    private Double latestClose;
    @Column(name = "latestTradingDate")
    @Temporal(TemporalType.DATE)
    private Date latestTradingDate;
    @Column(name = "todaysChange")
    private Double todaysChange;
    @Column(name = "sharesTraded")
    private Double sharesTraded;
    @Column(name = "oneYearPriceChange")
    private Double oneYearPriceChange;
    @Column(name = "epsPercentile")
    private Double epsPercentile;
    @Column(name = "pePercentile")
    private Double pePercentile;
    @Column(name = "pbPercentile")
    private Double pbPercentile;
    @Column(name = "roePercentile")
    private Double roePercentile;
    @Column(name = "roaPercentile")
    private Double roaPercentile;
    @Column(name = "epsHigh")
    private Double epsHigh;
    @Column(name = "peHigh")
    private Double peHigh;
    @Column(name = "pbHigh")
    private Double pbHigh;
    @Column(name = "roeHigh")
    private Double roeHigh;
    @Column(name = "roaHigh")
    private Double roaHigh;
    @Column(name = "epsLow")
    private Double epsLow;
    @Column(name = "peLow")
    private Double peLow;
    @Column(name = "pbLow")
    private Double pbLow;
    @Column(name = "roeLow")
    private Double roeLow;
    @Column(name = "roaLow")
    private Double roaLow;

    public Tearsheetderivedtable() {
    }

    public Tearsheetderivedtable(Integer idtearsheetderivedtable) {
        this.idtearsheetderivedtable = idtearsheetderivedtable;
    }

    public Integer getIdtearsheetderivedtable() {
        return idtearsheetderivedtable;
    }

    public void setIdtearsheetderivedtable(Integer idtearsheetderivedtable) {
        this.idtearsheetderivedtable = idtearsheetderivedtable;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getWeightedAvePrice() {
        return weightedAvePrice;
    }

    public void setWeightedAvePrice(Double weightedAvePrice) {
        this.weightedAvePrice = weightedAvePrice;
    }

    public Date getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(Date tradingDate) {
        this.tradingDate = tradingDate;
    }

    public Double getBetaMonthly() {
        return betaMonthly;
    }

    public void setBetaMonthly(Double betaMonthly) {
        this.betaMonthly = betaMonthly;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Double getSharesOutstanding() {
        return sharesOutstanding;
    }

    public void setSharesOutstanding(Double sharesOutstanding) {
        this.sharesOutstanding = sharesOutstanding;
    }

    public Double getSharesFloat() {
        return sharesFloat;
    }

    public void setSharesFloat(Double sharesFloat) {
        this.sharesFloat = sharesFloat;
    }

    public Double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Double marketCap) {
        this.marketCap = marketCap;
    }

    public Double getDaysTraded() {
        return daysTraded;
    }

    public void setDaysTraded(Double daysTraded) {
        this.daysTraded = daysTraded;
    }

    public Double getAverageVolume() {
        return averageVolume;
    }

    public void setAverageVolume(Double averageVolume) {
        this.averageVolume = averageVolume;
    }

    public Double getAverageNoofTrades() {
        return averageNoofTrades;
    }

    public void setAverageNoofTrades(Double averageNoofTrades) {
        this.averageNoofTrades = averageNoofTrades;
    }

    public Double getAverageSharesPerTrade() {
        return averageSharesPerTrade;
    }

    public void setAverageSharesPerTrade(Double averageSharesPerTrade) {
        this.averageSharesPerTrade = averageSharesPerTrade;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

    public Double getPe() {
        return pe;
    }

    public void setPe(Double pe) {
        this.pe = pe;
    }

    public Double getPbv() {
        return pbv;
    }

    public void setPbv(Double pbv) {
        this.pbv = pbv;
    }

    public Double getRoe() {
        return roe;
    }

    public void setRoe(Double roe) {
        this.roe = roe;
    }

    public Double getRoa() {
        return roa;
    }

    public void setRoa(Double roa) {
        this.roa = roa;
    }

    public Double getRelativeStrength() {
        return relativeStrength;
    }

    public void setRelativeStrength(Double relativeStrength) {
        this.relativeStrength = relativeStrength;
    }

    public Double getFiftyDayMAVsTwoHundreadDayMA() {
        return fiftyDayMAVsTwoHundreadDayMA;
    }

    public void setFiftyDayMAVsTwoHundreadDayMA(Double fiftyDayMAVsTwoHundreadDayMA) {
        this.fiftyDayMAVsTwoHundreadDayMA = fiftyDayMAVsTwoHundreadDayMA;
    }

    public Double getPriceVsFiftyTwoWeekHigh() {
        return priceVsFiftyTwoWeekHigh;
    }

    public void setPriceVsFiftyTwoWeekHigh(Double priceVsFiftyTwoWeekHigh) {
        this.priceVsFiftyTwoWeekHigh = priceVsFiftyTwoWeekHigh;
    }

    public Double getRsi() {
        return rsi;
    }

    public void setRsi(Double rsi) {
        this.rsi = rsi;
    }

    public Double getMacd() {
        return macd;
    }

    public void setMacd(Double macd) {
        this.macd = macd;
    }

    public Double getOneWeekPriceChange() {
        return oneWeekPriceChange;
    }

    public void setOneWeekPriceChange(Double oneWeekPriceChange) {
        this.oneWeekPriceChange = oneWeekPriceChange;
    }

    public Double getFourWeekPriceChange() {
        return fourWeekPriceChange;
    }

    public void setFourWeekPriceChange(Double fourWeekPriceChange) {
        this.fourWeekPriceChange = fourWeekPriceChange;
    }

    public Double getTwelveWeekPriceChange() {
        return twelveWeekPriceChange;
    }

    public void setTwelveWeekPriceChange(Double twelveWeekPriceChange) {
        this.twelveWeekPriceChange = twelveWeekPriceChange;
    }

    public Double getYtd() {
        return ytd;
    }

    public void setYtd(Double ytd) {
        this.ytd = ytd;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getOneEightyDayAverage() {
        return oneEightyDayAverage;
    }

    public void setOneEightyDayAverage(Double oneEightyDayAverage) {
        this.oneEightyDayAverage = oneEightyDayAverage;
    }

    public Double getVarMonthlyAtFive() {
        return varMonthlyAtFive;
    }

    public void setVarMonthlyAtFive(Double varMonthlyAtFive) {
        this.varMonthlyAtFive = varMonthlyAtFive;
    }

    public Double getVarWeeklyAtFive() {
        return varWeeklyAtFive;
    }

    public void setVarWeeklyAtFive(Double varWeeklyAtFive) {
        this.varWeeklyAtFive = varWeeklyAtFive;
    }

    public Double getTotalReturn() {
        return totalReturn;
    }

    public void setTotalReturn(Double totalReturn) {
        this.totalReturn = totalReturn;
    }

    public Double getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(Double priceChange) {
        this.priceChange = priceChange;
    }

    public Double getProfitabilityChange() {
        return profitabilityChange;
    }

    public void setProfitabilityChange(Double profitabilityChange) {
        this.profitabilityChange = profitabilityChange;
    }

    public Double getSentimentChange() {
        return sentimentChange;
    }

    public void setSentimentChange(Double sentimentChange) {
        this.sentimentChange = sentimentChange;
    }

    public Double getDaysHigh() {
        return daysHigh;
    }

    public void setDaysHigh(Double daysHigh) {
        this.daysHigh = daysHigh;
    }

    public Double getDaysLow() {
        return daysLow;
    }

    public void setDaysLow(Double daysLow) {
        this.daysLow = daysLow;
    }

    public Double getFiftyHigh() {
        return fiftyHigh;
    }

    public void setFiftyHigh(Double fiftyHigh) {
        this.fiftyHigh = fiftyHigh;
    }

    public Double getFiftyLow() {
        return fiftyLow;
    }

    public void setFiftyLow(Double fiftyLow) {
        this.fiftyLow = fiftyLow;
    }

    public Double getBetaWeekly() {
        return betaWeekly;
    }

    public void setBetaWeekly(Double betaWeekly) {
        this.betaWeekly = betaWeekly;
    }

    public Double getVarWeeklyAtOne() {
        return varWeeklyAtOne;
    }

    public void setVarWeeklyAtOne(Double varWeeklyAtOne) {
        this.varWeeklyAtOne = varWeeklyAtOne;
    }

    public Double getVarMonthlyAtOne() {
        return varMonthlyAtOne;
    }

    public void setVarMonthlyAtOne(Double varMonthlyAtOne) {
        this.varMonthlyAtOne = varMonthlyAtOne;
    }

    public Double getDaysHighLowPercentile() {
        return daysHighLowPercentile;
    }

    public void setDaysHighLowPercentile(Double daysHighLowPercentile) {
        this.daysHighLowPercentile = daysHighLowPercentile;
    }

    public Double getFiftyTwoHighLowPercentile() {
        return fiftyTwoHighLowPercentile;
    }

    public void setFiftyTwoHighLowPercentile(Double fiftyTwoHighLowPercentile) {
        this.fiftyTwoHighLowPercentile = fiftyTwoHighLowPercentile;
    }

    public Double getLatestClose() {
        return latestClose;
    }

    public void setLatestClose(Double latestClose) {
        this.latestClose = latestClose;
    }

    public Date getLatestTradingDate() {
        return latestTradingDate;
    }

    public void setLatestTradingDate(Date latestTradingDate) {
        this.latestTradingDate = latestTradingDate;
    }

    public Double getTodaysChange() {
        return todaysChange;
    }

    public void setTodaysChange(Double todaysChange) {
        this.todaysChange = todaysChange;
    }

    public Double getSharesTraded() {
        return sharesTraded;
    }

    public void setSharesTraded(Double sharesTraded) {
        this.sharesTraded = sharesTraded;
    }

    public Double getOneYearPriceChange() {
        return oneYearPriceChange;
    }

    public void setOneYearPriceChange(Double oneYearPriceChange) {
        this.oneYearPriceChange = oneYearPriceChange;
    }

    public Double getEpsPercentile() {
        return epsPercentile;
    }

    public void setEpsPercentile(Double epsPercentile) {
        this.epsPercentile = epsPercentile;
    }

    public Double getPePercentile() {
        return pePercentile;
    }

    public void setPePercentile(Double pePercentile) {
        this.pePercentile = pePercentile;
    }

    public Double getPbPercentile() {
        return pbPercentile;
    }

    public void setPbPercentile(Double pbPercentile) {
        this.pbPercentile = pbPercentile;
    }

    public Double getRoePercentile() {
        return roePercentile;
    }

    public void setRoePercentile(Double roePercentile) {
        this.roePercentile = roePercentile;
    }

    public Double getRoaPercentile() {
        return roaPercentile;
    }

    public void setRoaPercentile(Double roaPercentile) {
        this.roaPercentile = roaPercentile;
    }

    public Double getEpsHigh() {
        return epsHigh;
    }

    public void setEpsHigh(Double epsHigh) {
        this.epsHigh = epsHigh;
    }

    public Double getPeHigh() {
        return peHigh;
    }

    public void setPeHigh(Double peHigh) {
        this.peHigh = peHigh;
    }

    public Double getPbHigh() {
        return pbHigh;
    }

    public void setPbHigh(Double pbHigh) {
        this.pbHigh = pbHigh;
    }

    public Double getRoeHigh() {
        return roeHigh;
    }

    public void setRoeHigh(Double roeHigh) {
        this.roeHigh = roeHigh;
    }

    public Double getRoaHigh() {
        return roaHigh;
    }

    public void setRoaHigh(Double roaHigh) {
        this.roaHigh = roaHigh;
    }

    public Double getEpsLow() {
        return epsLow;
    }

    public void setEpsLow(Double epsLow) {
        this.epsLow = epsLow;
    }

    public Double getPeLow() {
        return peLow;
    }

    public void setPeLow(Double peLow) {
        this.peLow = peLow;
    }

    public Double getPbLow() {
        return pbLow;
    }

    public void setPbLow(Double pbLow) {
        this.pbLow = pbLow;
    }

    public Double getRoeLow() {
        return roeLow;
    }

    public void setRoeLow(Double roeLow) {
        this.roeLow = roeLow;
    }

    public Double getRoaLow() {
        return roaLow;
    }

    public void setRoaLow(Double roaLow) {
        this.roaLow = roaLow;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtearsheetderivedtable != null ? idtearsheetderivedtable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tearsheetderivedtable)) {
            return false;
        }
        Tearsheetderivedtable other = (Tearsheetderivedtable) object;
        if ((this.idtearsheetderivedtable == null && other.idtearsheetderivedtable != null) || (this.idtearsheetderivedtable != null && !this.idtearsheetderivedtable.equals(other.idtearsheetderivedtable))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Tearsheetderivedtable [idtearsheetderivedtable=" + idtearsheetderivedtable + ", ticker=" + ticker
				+ ", stockName=" + stockName + ", weightedAvePrice=" + weightedAvePrice + ", tradingDate=" + tradingDate
				+ ", betaMonthly=" + betaMonthly + ", sector=" + sector + ", sharesOutstanding=" + sharesOutstanding
				+ ", sharesFloat=" + sharesFloat + ", marketCap=" + marketCap + ", daysTraded=" + daysTraded
				+ ", averageVolume=" + averageVolume + ", averageNoofTrades=" + averageNoofTrades
				+ ", averageSharesPerTrade=" + averageSharesPerTrade + ", eps=" + eps + ", pe=" + pe + ", pbv=" + pbv
				+ ", roe=" + roe + ", roa=" + roa + ", relativeStrength=" + relativeStrength
				+ ", fiftyDayMAVsTwoHundreadDayMA=" + fiftyDayMAVsTwoHundreadDayMA + ", priceVsFiftyTwoWeekHigh="
				+ priceVsFiftyTwoWeekHigh + ", rsi=" + rsi + ", macd=" + macd + ", oneWeekPriceChange="
				+ oneWeekPriceChange + ", fourWeekPriceChange=" + fourWeekPriceChange + ", twelveWeekPriceChange="
				+ twelveWeekPriceChange + ", ytd=" + ytd + ", volume=" + volume + ", oneEightyDayAverage="
				+ oneEightyDayAverage + ", varMonthlyAtFive=" + varMonthlyAtFive + ", varWeeklyAtFive="
				+ varWeeklyAtFive + ", totalReturn=" + totalReturn + ", priceChange=" + priceChange
				+ ", profitabilityChange=" + profitabilityChange + ", sentimentChange=" + sentimentChange
				+ ", daysHigh=" + daysHigh + ", daysLow=" + daysLow + ", fiftyHigh=" + fiftyHigh + ", fiftyLow="
				+ fiftyLow + ", betaWeekly=" + betaWeekly + ", varWeeklyAtOne=" + varWeeklyAtOne + ", varMonthlyAtOne="
				+ varMonthlyAtOne + ", daysHighLowPercentile=" + daysHighLowPercentile + ", fiftyTwoHighLowPercentile="
				+ fiftyTwoHighLowPercentile + ", latestClose=" + latestClose + ", latestTradingDate="
				+ latestTradingDate + ", todaysChange=" + todaysChange + ", sharesTraded=" + sharesTraded
				+ ", oneYearPriceChange=" + oneYearPriceChange + ", epsPercentile=" + epsPercentile + ", pePercentile="
				+ pePercentile + ", pbPercentile=" + pbPercentile + ", roePercentile=" + roePercentile
				+ ", roaPercentile=" + roaPercentile + ", epsHigh=" + epsHigh + ", peHigh=" + peHigh + ", pbHigh="
				+ pbHigh + ", roeHigh=" + roeHigh + ", roaHigh=" + roaHigh + ", epsLow=" + epsLow + ", peLow=" + peLow
				+ ", pbLow=" + pbLow + ", roeLow=" + roeLow + ", roaLow=" + roaLow + "]";
	}

   
    
}
