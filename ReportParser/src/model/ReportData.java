package model;

import java.text.*;
import java.util.*;
import java.util.regex.*;

public class ReportData {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("H:mm");

    private final Pattern pattern = Pattern.compile("[0-9]*[.,-:]?[0-9]+|[а-яА-Яa-zA-ZЁ].+");

    private Date time;

    private double cycleNumber;

    private String depth;

    private String depthAdjusted;

    private double gain;

    private double count;

    private double chargeDepth;

    private double quantituEDS;

    private double chargeWeight;

    private double timeSSV;

    private double temperature;

    private String note;

    public ReportData(
            Date time,
            double cycleNumber,
            String depth,
            String depthAdjusted,
            double gain,
            double count,
            double chargeDepth,
            double quantituEDS,
            double chargeWeight,
            double timeSSV,
            double temperature,
            String note
    ) {
        this.time = time;
        this.cycleNumber = cycleNumber;
        this.depth = depth;
        this.depthAdjusted = depthAdjusted;
        this.gain = gain;
        this.count = count;
        this.chargeDepth = chargeDepth;
        this.quantituEDS = quantituEDS;
        this.chargeWeight = chargeWeight;
        this.timeSSV = timeSSV;
        this.temperature = temperature;
        this.note = note;
    }

    public ReportData(String line) throws ParseException {
        convertLineToData(line);
    }

    private void convertLineToData(String line) throws ParseException {
        List<String> dataFromTxt = new ArrayList<>();
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            dataFromTxt.add(line.substring(matcher.start(), matcher.end()).replace(",", "."));
        }
        time = dateFormat.parse(dataFromTxt.get(0));
        cycleNumber = Double.parseDouble(dataFromTxt.get(1));
        depth = dataFromTxt.get(2);
        depthAdjusted = dataFromTxt.get(3);
        gain = Double.parseDouble(dataFromTxt.get(4));
        count = Double.parseDouble(dataFromTxt.get(5));
        chargeDepth = Double.parseDouble(dataFromTxt.get(6));
        quantituEDS = Double.parseDouble(dataFromTxt.get(7));
        chargeWeight = Double.parseDouble(dataFromTxt.get(8));
        timeSSV = Double.parseDouble(dataFromTxt.get(9));
        temperature = Double.parseDouble(dataFromTxt.get(10));
        note = dataFromTxt.get(11);
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getCycleNumber() {
        return cycleNumber;
    }

    public void setCycleNumber(double cycleNumber) {
        this.cycleNumber = cycleNumber;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getDepthAdjusted() {
        return depthAdjusted;
    }

    public void setDepthAdjusted(String depthAdjusted) {
        this.depthAdjusted = depthAdjusted;
    }

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getChargeDepth() {
        return chargeDepth;
    }

    public void setChargeDepth(double chargeDepth) {
        this.chargeDepth = chargeDepth;
    }

    public double getQuantituEDS() {
        return quantituEDS;
    }

    public void setQuantituEDS(double quantituEDS) {
        this.quantituEDS = quantituEDS;
    }

    public double getChargeWeight() {
        return chargeWeight;
    }

    public void setChargeWeight(double chargeWeight) {
        this.chargeWeight = chargeWeight;
    }

    public double getTimeSSV() {
        return timeSSV;
    }

    public void setTimeSSV(double timeSSV) {
        this.timeSSV = timeSSV;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "\nВремя = " + dateFormat.format(time) +
                "\n Номер цикла = " + cycleNumber +
                "\n Глубина = " + depth +
                "\n Откорректированная глубина = " + depthAdjusted +
                "\n Усиление = " + gain +
                "\n Кол. выст. рел.  = " + count +
                "\n Глубина заряда = " + chargeDepth +
                "\n Кол-во ЭДС = " + quantituEDS +
                "\n Вес заряда = " + chargeWeight +
                "\n Время ССВ = " + timeSSV +
                "\n Температура = " + temperature +
                "\n Примечание = " + note + '\n';
    }
}
