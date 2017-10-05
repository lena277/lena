package com.example.demo.enums;

public enum VactionType { 
TYPE1("type1"), 
TYPE2("type2"); 
 
private String type; 


 VactionType(String type) { 
this.type=type; 
} 
public String type() { return this.type; } 
} 
