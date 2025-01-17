/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assets;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author anasm
 */
public class TextFieldLimit extends PlainDocument {
   private int limit;
   
   public TextFieldLimit(int limit) {
      super();
      this.limit = limit;
   }
   public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
      if (str == null)
         return;
      if ((getLength() + str.length()) <= limit) {
         super.insertString(offset, str, attr);
      }
   }
}
