class User
  include Mongoid::Document

  ## Database authenticatable
  field :first_name, type: String
  field :last_name, type: String
  field :email, type: String
  field :authentication_token, type: String
  field :encrypted_password, type: String
  ## Recoverable
  field :reset_password_token,   type: String
  field :reset_password_sent_at, type: Time
  ## Rememberable
  field :remember_created_at, type: Time

  #Setting the auth token#
  before_save :ensure_authentication_token
  before_save :ensure_encrypted_password
  #File Validations#
  validates_presence_of :first_name, :last_name, :email, :encrypted_password
  validates_uniqueness_of :email, :authentication_token

  def ensure_authentication_token
    if authentication_token.blank?
      self.authentication_token = generate_authentication_token
    end
  end

  #Validate the password and returns the user authenticated
  def self.authenticate(email, password)
    user = User.find_by(email: email) || nil
    if !user.nil? && user.encrypted_password == Digest::MD5.hexdigest(password)
      user
    else
      nil
    end
  end

  private

  #Encrypte the password
  def ensure_encrypted_password
    self.encrypted_password = Digest::MD5.hexdigest(self.encrypted_password) if new_record?
  end

  def generate_authentication_token
    loop do
      token = SecureRandom.urlsafe_base64(nil, false)
      break token unless User.where(authentication_token: token).first
    end
  end
end
