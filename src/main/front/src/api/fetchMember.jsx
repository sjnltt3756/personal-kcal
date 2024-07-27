import axiosInstance from "./axiosInstance";

export const loginMember = async (loginDTO) => {
  try {
    const response = await axiosInstance.post("/member/login", loginDTO);
    return response.data;
  } catch (error) {
    console.error("Error logging in:", error);
    throw error;
  }
};

export const registerMember = async (registerDTO) => {
  try {
    const response = await axiosInstance.post("/member/register", registerDTO);
    return response.data;
  } catch (error) {
    console.error("Error registering:", error);
    throw error;
  }
};

export const viewMember = async (id) => {
  try {
    const response = await axiosInstance.get(`/member/view/${id}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching member:", error);
    throw error;
  }
};

export const updateMember = async (id, updateDTO) => {
  try {
    const response = await axiosInstance.put(`/member/update/${id}`, updateDTO);
    return response.data;
  } catch (error) {
    console.error("Error updating member:", error);
    throw error;
  }
};
