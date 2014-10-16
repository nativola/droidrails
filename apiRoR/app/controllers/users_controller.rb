class UsersController < ApplicationController
	respond_to :json  

	#before_action :authenticate_user_from_token!
	
  # POST /users
	def create
		resp = {} 
      user = User.create(user_params)
     	if user.valid?
        if user.save
          return render :json => user.as_json
        end
      end
      render json: user.errors.full_messages 
	end

  # GET /users/:id
  def show
    render json: User.find(params[:id])
  end

  # GET /users
  def index
    render json: User.all
  end
 
  # PATCH/PUT /users/:id
  def update
    user = User.find(params[:id])
    if user.update(user_params)
      return render json: user
    end
    render json: user.errors.full_messages 
  end

  # DELETE /users/:id
  def destroy
    user = User.find(params[:id]) 
    if !user.nil?
      return render json: user.delete
    end
    render json: "The user was not found."
  end

  private

  def user_params
    params.require(:user).permit(:first_name)
  end

end
