package cartoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class StnData {
    private ArrayList<String> _stnNameArray;
    private ArrayList<String> _stnLineArray;
    private ArrayList<Double> _stnXArray;
    private ArrayList<Double> _stnYArray;
    private int[][] _stnFareMatrix = new int[165][165];

    public StnData() {
        this.setUpStnNameArray();
        this.setUpStnLineArray();
        this.setUpStnXArray();
        this.setUpStnYArray();
        this.setUpFareArray();
    }

    public void setUpStnNameArray() {
        _stnNameArray = new ArrayList<String>(165);
        _stnNameArray.addAll(Arrays.asList("","Dilshad Garden","Jhilmil","Mansarovar Park","Shahdara","Welcome",
                                          "Seelampur","Shastri Park","Kashmere Gate","Tis Hazari","Pul Bangash",
                                          "Pratap Nagar","Shastri Nagar","Mundka","Rajdhani Park",
                                          "Nangloi Railway Station","Nangloi","Surajmal Stadium","Udyog Nagar",
                                          "Peeragarhi","Paschim Vihar West","Paschim Vihar East","Madipur",
                                          "Shivaji Park","Punjabi Bagh","Ashok Park Main","Satguru Ram Singh Marg",
                                          "Inderlok","Kanhiya Nagar","Keshav Puram","Netaji Subhash Place",
                                          "Kohat Enclave","Pitam Pura","Rohini East","Rohini West","Rithala",
                                          "Samaypur Badli","Rohini Sector 18","Haiderpur","Jahangirpuri","Adarsh Nagar",
                                          "Azadpur","Model Town","GTB Nagar","Viswa Vidyalaya","Vidhan Sabha",
                                          "Civil Lines","Chandni Chowk","Chawri Bazar","New Delhi","Rajiv Chowk",
                                          "Patel Chowk","Central Secretariat","Udyog Bhawan","Race Course","Jor Bagh",
                                          "INA","AIIMS","Green Park","Hauz Khas","Malviya Nagar","Saket","Qutub Minar",
                                          "Chhattarpur","Sultanpur","Ghitorni","Arjan Garh","Guru Dronacharya",
                                          "Sikandarpur","MG Road","IFFCO Chowk","HUDA City Centre","Vaishali",
                                          "Kaushambi","Anand Vihar","Karkarduma","Preet Vihar","Nirman Vihar",
                                          "Laxmi Nagar","Noida City Centre","Noida Golf Course","Botanical Garden",
                                          "Noida Sector 18","Noida Sector 16","Noida Sector 15","New Ashok Nagar",
                                          "Mayur Vihar Extension","Mayur Vihar I","Akshardham","Yamuna Bank",
                                          "Indraprastha","Pragati Maidan","Mandi House","Barakhamba Road",
                                          "Ramakrishna Ashram Marg","Jhandewalan","Karol Bagh","Rajendra Place",
                                          "Patel Nagar","Shadipur","Kirti Nagar","Moti Nagar","Ramesh Nagar",
                                          "Rajouri Garden","Tagore Garden","Subhash Nagar","Tilak Nagar",
                                          "Janakpuri East","Janakpuri West","Uttam Nagar East","Uttam Nagar West",
                                          "Nawada","Dwarka Morh","Dwarka","Dwarka Sector 14","Dwarka Sector 13",
                                          "Dwarka Sector 12","Dwarka Sector 11","Dwarka Sector 10","Dwarka Sector 9",
                                          "Dwarka Sector 8","Dwarka Sector 21","ITO","Janpath","Khan Market",
                                          "Jawaharlal Nehru Stadium","Jangpura","Lajpat Nagar","Moolchand",
                                          "Kailash Colony","Nehru Place","Kalkaji Mandir","Govind Puri","Okhla",
                                          "Jasola Apollo","Sarita Vihar","Mohan Estate","Tuglakabad","Badarpur","Sarai",
                                          "NHPC Chowk","Mewala Maharajpur","Sector 28","Badkhal Mor","Old Faridabad",
                                          "Neelam Chowk Ajronda","Bata Chowk","Escorts Mujesar","Shivaji Stadium",
                                          "Dhaula Khan","Delhi Aerocity","IGI Airport","Phase 2","Phase 3",
                                          "Moulsari Avenue","Cyber City","Belvedere Towers","Inderlok","Kashmere Gate",
                                          "Kirti Nagar","New Delhi","Mandi House","Central Secretariat","Rajiv Chowk",
                                          "Sikandarpur","Dwarka Sector 21"));
    }

    public void setUpStnLineArray() {
        _stnLineArray = new ArrayList<String>(165);
        _stnLineArray.addAll(Arrays.asList("","red","red","red","red","red","red","red","red","red","red","red","red",
                                          "green","green","green","green","green","green","green","green","green",
                                          "green","green","green","green","green","red","red","red","red","red","red",
                                          "red","red","red","yellow","yellow","yellow","yellow","yellow","yellow",
                                          "yellow","yellow","yellow","yellow","yellow","yellow","yellow","yellow",
                                          "yellow","yellow","yellow","yellow","yellow","yellow","yellow","yellow",
                                          "yellow","yellow","yellow","yellow","yellow","yellow","yellow","yellow",
                                          "yellow","yellow","yellow","yellow","yellow","yellow","blue","blue","blue",
                                          "blue","blue","blue","blue","blue","blue","blue","blue","blue","blue","blue",
                                          "blue","blue","blue","blue","blue","blue","blue","blue","blue","blue","blue",
                                          "blue","blue","blue","blue","blue","blue","blue","blue","blue","blue","blue",
                                          "blue","blue","blue","blue","blue","blue","blue","blue","blue","blue","blue",
                                          "blue","blue","blue","violet","violet","violet","violet","violet","violet",
                                          "violet","violet","violet","violet","violet","violet","violet","violet",
                                          "violet","violet","violet","violet","violet","violet","violet","violet",
                                          "violet","violet","violet","violet","orange","orange","orange","orange",
                                          "rapid","rapid","rapid","rapid","rapid","green","yellow","green","orange",
                                          "violet","violet","blue","rapid","orange"));
    }

    public void setUpStnXArray() {
        _stnXArray = new ArrayList<Double>(174);
        _stnXArray.addAll(Arrays.asList(0.0,903.18,865.63,828.08,790.53,749.42,722.74,680.81,550.5,528.66,500.1,466.5,
                434.58,96.93,113.98,131.03,148.09,165.14,182.19,199.25,216.3,233.36,253.32,278.35,328.75,365.71,399.31,
                399.31,380.83,364.03,344.44,315.1,285.75,256.41,227.06,197.72,373.92,391.06,408.19,425.33,442.46,466.73,
                483.48,500.23,516.99,533.74,547.68,550.5,550.5,550.5,550.5,550.5,550.5,550.5,550.5,550.5,550.5,550.05,
                535.64,509.44,487.42,472.54,457.66,442.78,427.89,413.01,398.13,383.24,368.36,353.48,338.59,323.71,
                928.47,877.57,826.67,789.48,770.65,748.77,726.89,918.15,886.48,843.05,827.55,816.97,806.38,795.8,785.21,
                774.63,731.92,686.57,663.05,638.1,595.85,575.69,533.7,520.35,502.81,479.22,452.58,425.95,399.91,365.71,
                332.11,298.51,259.31,239.26,225.15,211.84,198.68,173.12,147.74,122.33,100.39,96.93,96.93,96.93,96.93,
                96.93,96.93,96.93,96.93,96.93,626.09,570.73,595.85,624.19,626.09,626.09,626.09,632.89,653.57,668.65,
                680.16,691.67,703.19,714.7,726.22,737.73,749.25,760.76,772.28,783.79,795.3,806.82,818.33,829.85,841.36,
                852.88,525.3,390.91,281.71,231.32,348.91,341.36,316.16,290.96,316.16,399.31,550.5,399.31,545.46,595.85,
                555.54,555.54,364.8,101.97,622.36,569.89,711.25,607.08,620.22,390.2,399.31,381.49,394.6));
    }

    public void setUpStnYArray() {
        _stnYArray = new ArrayList<Double>(174);
        _stnYArray.addAll(Arrays.asList(0.0,96.83,134.43,172.02,209.62,250.78,277.45,298.51,298.51,298.51,298.51,298.51,
                298.51,197.72,214.77,231.82,248.88,265.96,282.98,300.04,317.09,334.15,346.41,348.91,348.91,348.91,
                372.42,294.06,280.03,263.23,243.65,214.3,184.96,155.61,126.27,96.92,71.72,88.82,105.92,123.02,140.11,
                164.34,181.09,197.85,214.6,231.36,252.35,338.83,366.55,404.36,454.74,490.02,525.3,553.46,581.63,609.8,
                654.21,687.55,716.55,742.75,764.76,779.64,794.52,809.41,824.29,839.17,854.06,868.94,883.82,898.71,
                913.59,928.47,348.91,348.91,348.91,361.9,380.78,402.64,424.5,535.61,567.29,588.52,575.56,564.98,554.4,
                543.81,533.23,522.64,479.94,464.82,488.34,511.8,495.06,474.9,432.9,419.55,405.14,399.3,399.3,399.3,
                399.3,399.3,399.3,399.3,400.25,409.02,422.37,435.63,449.05,474.29,499.71,525.1,553.01,600.89,638.06,
                675.22,712.39,749.56,786.73,823.89,861.06,898.23,454.74,502.12,550.49,566.53,591.23,642.67,674.8,
                706.12,729.16,744.24,755.75,767.27,778.78,790.3,801.81,813.32,824.84,836.35,847.87,859.38,870.9,882.41,
                893.93,905.44,916.95,928.47,474.9,609.29,718.48,768.88,864.5,831.75,806.56,831.75,856.95,299.1,293.5,
                394.26,404.36,500.1,525.3,454.74,880.26,898.23,488.04,548.12,462.01,506.29,514.14,343.14,321.22,350.02,
                359.49));
    }

    public void setUpFareArray() {
        Scanner scanner = new Scanner(StnData.class.getResourceAsStream("assets/fareValues.txt"));
        for (int i=0; i<165; i++) {
            for (int j=0; j<165; j++) {
                _stnFareMatrix[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
    }

    public String getStnName(int stn) {
        return _stnNameArray.get(stn);
    }

    public String getLine(int stn) {
        return _stnLineArray.get(stn);
    }

    public Double getStnX(int stn) {
        return _stnXArray.get(stn);
    }

    public Double getStnY(int stn) {
        return _stnYArray.get(stn);
    }

    public int getFare(int i, int j) {
        return _stnFareMatrix[i][j];

    }
}
