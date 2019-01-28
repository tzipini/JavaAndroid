package com.example.ourex.takengo.models.entities;

/**
 * •	מספר לקוח
 •	הזמנה פתוחה\סגורה
 •	מספר מכונית
 •	מועד תחילת ההשכרה
 •	מועד סיום ההשכרה
 •	ערך תחילת קילומטרז'
 •	ערך סוף קילומטרז'
 •	האם התבצע מילוי דלק ?
 •	אם מילאו דלק אזי יש לציין כמה ליטרים מולאו.
 •	סכום סופי לחיוב
 •	מספר הזמנה

 */
public class Order {
    protected Integer customerNo;
    protected boolean opened;
    protected Integer carNumber;
}
