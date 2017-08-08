/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.hcss.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hcss.bean.DrivingLicenseTO;
import com.hcss.bean.InsuranceTO;
import com.hcss.delegate.UserPersonalDelegate;
import com.hcss.utill.UtilConstants;

/**
 * MyEclipse Struts Creation date: 09-01-2012
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 * @struts.action-forward name="failure" path="/Status.jsp"
 * @struts.action-forward name="success" path="/ViewInsuranceDetails.jsp"
 */
public class ViewInsurenceInfoAction extends Action {
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
		HttpSession session = request.getSession();
		int userid = (Integer) session.getAttribute("userid");
		Vector<InsuranceTO> vInsuranceTOs = null;
		try {
			vInsuranceTOs = new UserPersonalDelegate()
					.viewInsurenceDetails(userid);
			if (vInsuranceTOs != null) {
				request.setAttribute("vInsuranceTOs", vInsuranceTOs);
				request.setAttribute("status",
						UtilConstants._VIEW_NSURANCE_DETAILS);
				return mapping.findForward("success");
			} else {
				request.setAttribute("status",
						UtilConstants._VIEW_NSURANCE_DETAILS_FAIL);
				return mapping.findForward("failure");
			}
		} catch (Exception ce) {
			request.setAttribute("status", ce.getMessage());
			return mapping.findForward("failure");
		}
	}
}