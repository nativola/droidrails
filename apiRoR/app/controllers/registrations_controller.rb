class RegistrationsController < Devise::RegistrationsController
  respond_to :json
  skip_before_filter :verify_authenticity_token, :only => [:create]
  skip_before_filter :require_no_authentication
  skip_before_filter :authenticate_user_from_token!  
 
  def create 
    resp = {} 
    if !user_signed_in?
      resource = self.create_user
      #returns the validations errors array on failure
      unless resource[:user].present?
        return render :json => {:success => false, :errors => resource.errors}, :location => nil
      end  
      resource[:user].save

      resp[:user] = resource[:user]
      resp[:success] = true
      render :json => resp
      #respond_with(resp, :location => nil) 
    else
      resp[:errors] = {}
      resp[:errors][:session] = 'already logged in'
      respond_with resp, :location => nil, :status => :unprocessable_entity
    end
  end
  def create_user
    build_resource(sign_up_params)  
    #skip the email confirmation 
    #resource.skip_confirmation!
    if resource.save
      if resource.active_for_authentication?
        set_flash_message :notice, :signed_up if is_navigational_format?
        return :user => resource
      else
        set_flash_message :notice, :"signed_up_but_#{resource.inactive_message}" if is_navigational_format?
        expire_session_data_after_sign_in!
        return resource
      end
    else
      clean_up_passwords resource
      return resource
    end
  end
end

