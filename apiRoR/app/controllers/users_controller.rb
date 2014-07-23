class UsersController < ApplicationController
	respond_to :json  
	before_action :authenticate_user_from_token!, except: [ :create, :index ]
	
	def create
		resp = {} 
      user = User.create(user_params)
     	if user.valid?
     		user.save
     		resp[:user] = user
	      resp[:success] = true
	      render :json => resp
	    else
        Rails.logger.debug "Error #{user.errors.full_messages}"
        render json: user.errors.full_messages 
     	end
	end

  def index
    resp = {} 
    # Querying info by mongoid---> http://mongoid.org/en/mongoid/v3/querying.html
    @user = User.authenticate(params[:email],params[:encrypted_password])
    if !@user.nil?
      resp[:user] = @user
      resp[:success] = true
      render :json => resp   
    else
      resp[:errors] = {}
      resp[:errors][:session] = "The email and password are not valid!!."
      respond_with resp, :location => nil, :status => :unprocessable_entity
    end
  end

  def edit
   
  end

  def update

  end


  private

  def user_params
    params.require(:user).permit(:first_name, :last_name, :email, :encrypted_password)
  end

end
