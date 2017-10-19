package com.example.demo.enums;

public enum VactionType { 
SICK("sick"), 
PERSONAL("personal"); 
 
private String type; 


 VactionType(String type) { 
this.type=type; 
} 
public String type() { return this.type; } 
} 
