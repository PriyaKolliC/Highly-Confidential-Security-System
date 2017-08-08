/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.hcss.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.hcss.bean.DrivingLicenseTO;
import com.hcss.delegate.UserPersonalDelegate;
import com.hcss.utill.UtilConstants;

/**
 * MyEclipse Struts Creation date: 09-05-2012
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/updateLicenseDetailAction" name="DrivingLiesenceTO"
 *                scope="request" validate="true"
 * @struts.action-forward name="failure" path="/Status.jsp"
 * @struts.action-forward name="success" path="/Status.jsp"
 */
public class UpdateLicenseDetailAction extends Action {
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
		DrivingLicenseTO DrivingLiesenceTO = (DrivingLicenseTO) form;// TODO
																		// Auto-generated
																		// method
																		// stub
		boolean flag = false;
		try {
			flag = new UserPersonalDelegate()
					.updateLicenseDetails(DrivingLiesenceTO);
			if (flag) {
				request.setAttribute("status",
						UtilConstants._UPDATE_LICENSE_DETAILS);
				return mapping.findForward("success");
			} else {
				request.setAttribute("status",
						UtilConstants._UPDATE_LICENSE_DETAILS_FAIL);
				return mapping.findForward("failure");
			}
		} catch (Exception e) {
			System.out.println(e);
			request.setAttribute("status", e.getMessage());
			return mapping.findForward("failure");
		}
	}
}