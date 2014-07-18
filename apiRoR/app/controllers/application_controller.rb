class ApplicationController < ActionController::Base
  # Prevent CSRF attacks by raising an exception.
  # For APIs, you may want to use :null_session instead.
  #protect_from_forgery with: :exception

  before_filter :authenticate_user_from_token!
  # This is Devise's authentication
  skip_before_filter :authenticate_user!

  before_filter :configure_permitted_parameters, if: :devise_controller?
  #before_filter :configure_permitted_parameters, if: :devise_controller?
 

  #before_filter :check 
  #def check 
    #Rails.logger.debug "TOKEN" 
    #logger.debug  request.header['user_token']
  #end
  
  protected


  def configure_permitted_parameters
    devise_parameter_sanitizer.for(:sign_in) do |u|
      u.permit(:username, :email)
    end
    devise_parameter_sanitizer.for(:sign_up) do |u|
      u.permit( :email, :password, :password_confirmation, :first_name, :last_name)
    end
    devise_parameter_sanitizer.for(:account_update) do |u|
      u.permit( :email, :password, :password_confirmation, :first_name, :last_name)
    end
  end

  private

  # For this example, we are simply using token authentication 
  # via parameters. However, anyone could use Rails's token
  # authentication features to get the token from a header.
  def authenticate_user_from_token!
    user_token = params[:auth_token] || request.headers["HTTP_AUTH_TOKEN"]
    user       = user_token && User.find_by_authentication_token(user_token)
 
    if user && Devise.secure_compare(user.authentication_token, user_token) 
      sign_in user, store: false
      @current_user = user
    else
      render :json => {:success=>false, :message=>"You are not authorized to perform this action"}, :status=>401 if !params[:controller].include?('admin')
    end
  end
end
