/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.hcss.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import antlr.Lookahead;

import com.hcss.bean.LoginTO;
import com.hcss.cryptutil.Encryption;
import com.hcss.delegate.SecurityUserDelegate;
import com.hcss.utill.UtilConstants;

/**
 * MyEclipse Struts Creation date: 08-31-2012
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 * @struts.action-forward name="failure"
 *                        path="/RecoverPassword.jsp?status=Sorry Please Try again"
 * @struts.action-forward name="success"
 *                        path="/Status.jsp?status=Your new Password added Successfully"
 */
public class NewPasswordAction extends Action {
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		boolean flag = false;
		Encryption ec = new Encryption();
		int a[] = ec.ecies_ex(request.getParameter("password"));
		String abc = "";
		for (int i = 0; i < a.length; i++) {
			abc = abc + a[i] + ",";
		}
		LoginTO loginTO = (LoginTO) form;
		loginTO.setPassword(abc);
		try {
			flag = new SecurityUserDelegate().newPassword(loginTO);
			if (flag) {
				return mapping.findForward("success");
			} else {
				return mapping.findForward("failure");
			}
		} catch (Exception e) {
			request.setAttribute("status", e.getMessage());
			return mapping.findForward("failure");
		}
	}
}