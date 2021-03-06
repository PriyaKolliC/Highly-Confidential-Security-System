/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.hcss.action;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hcss.bean.ProfileTO;
import com.hcss.delegate.SecurityUserDelegate;
import com.hcss.utill.UtilConstants;
import com.sun.org.apache.commons.beanutils.BeanUtils;

/**
 * MyEclipse Struts Creation date: 08-31-2012
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 * @struts.action-forward name="failure" path=
 *                        "/RecoverPassword.jsp?status=Your Entered Wrong details Please try Again"
 * @struts.action-forward name="success" path="/NewPassword.jsp"
 */
public class RecoverPasswordAction extends Action {
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
		boolean flag = true;
		ProfileTO profileTO = (ProfileTO) form;
		try {
			flag = new SecurityUserDelegate().passwordRecovery(profileTO);
			if (flag) {
				String loginid = profileTO.getLoginid();
				request.setAttribute("loginid", loginid);
				return mapping.findForward("success");
			} else {
				request.setAttribute("status",
						UtilConstants._RECOVER_PASSWORD_FAILED);
				return mapping.findForward("failure");
			}
		} catch (Exception e) {
			request.setAttribute("status", e.getMessage());
			return mapping.findForward("failure");

		}
	}
}