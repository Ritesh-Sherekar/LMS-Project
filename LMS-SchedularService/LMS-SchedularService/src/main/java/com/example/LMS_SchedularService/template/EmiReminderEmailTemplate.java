package com.example.LMS_SchedularService.template;


public class EmiReminderEmailTemplate {
    public static final String EMI_REMINDER_EMAIL_HTML =
            "<!doctype html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "  <meta charset=\"utf-8\" />\n" +
                    "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                    "  <title>EMI Payment Reminder</title>\n" +
                    "  <style>\n" +
                    "    body { font-family: Arial, Helvetica, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }\n" +
                    "    .container { max-width: 650px; margin: 30px auto; background: #ffffff; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); }\n" +
                    "    .header { padding: 24px; background: linear-gradient(90deg,#ca8a04,#fbbf24); color: #fff; border-radius: 8px 8px 0 0; }\n" +
                    "    .header h1 { margin: 0; font-size: 22px; font-weight: 600; }\n" +
                    "    .content { padding: 24px; color: #333; line-height: 1.6; font-size: 15px; }\n" +
                    "    table { width: 100%; border-collapse: collapse; margin-top: 20px; }\n" +
                    "    td { padding: 10px; border-bottom: 1px solid #e5e7eb; }\n" +
                    "    .footer { padding: 16px 24px; font-size: 12px; color: #777; background: #fafafa; border-radius: 0 0 8px 8px; text-align: center; }\n" +
                    "    @media (max-width: 420px) { .content, .header { padding: 16px; } }\n" +
                    "  </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"container\">\n" +
                    "  <div class=\"header\">\n" +
                    "     <h1>EMI Payment Reminder</h1>\n" +
                    "  </div>\n" +
                    "  <div class=\"content\">\n" +
                    "     <p>Hi <strong>{{CUSTOMER_NAME}}</strong>,</p>\n" +
                    "     <p>This is a friendly reminder that your upcoming EMI is due soon.</p>\n" +
                    "     <p>Please review the EMI details below and ensure timely payment to avoid late fees.</p>\n" +
                    "\n" +
                    "     <table>\n" +
                    "       <tr>\n" +
                    "         <td><strong>Loan ID:</strong></td>\n" +
                    "         <td>{{LOAN_ID}}</td>\n" +
                    "       </tr>\n" +
                    "       <tr>\n" +
                    "         <td><strong>Loan Type:</strong></td>\n" +
                    "         <td>{{LOAN_TYPE}}</td>\n" +
                    "       </tr>\n" +
                    "       <tr>\n" +
                    "         <td><strong>EMI Amount:</strong></td>\n" +
                    "         <td>{{EMI_AMOUNT}}</td>\n" +
                    "       </tr>\n" +
                    "       <tr>\n" +
                    "         <td><strong>Due Date:</strong></td>\n" +
                    "         <td>{{DUE_DATE}}</td>\n" +
                    "       </tr>\n" +
                    "     </table>\n" +
                    "\n" +
                    "     <p>Please make your payment before the due date to maintain a good repayment record.</p>\n" +
                    "     <p>If you have any questions, feel free to contact our support team.</p>\n" +
                    "  </div>\n" +
                    "  <div class=\"footer\">\n" +
                    "     © " + java.time.Year.now().getValue() + " Loan Management System. All rights reserved.\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";

    /**
     * Generate EMI Reminder Email HTML
     */
    public static String getEmiReminderHtml(
            String firstName,
            int loanId,
            String loanType,
            Double emiAmount,
            java.time.LocalDate dueDate
    ) {
        return EMI_REMINDER_EMAIL_HTML
                .replace("{{CUSTOMER_NAME}}", escapeHtml(firstName))
                .replace("{{LOAN_ID}}", escapeHtml(String.valueOf(loanId)))
                .replace("{{LOAN_TYPE}}", escapeHtml(loanType))
                .replace("{{EMI_AMOUNT}}", escapeHtml(String.valueOf(emiAmount)))
                .replace("{{DUE_DATE}}", escapeHtml(String.valueOf(dueDate)));
    }

    private static String escapeHtml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }

}
