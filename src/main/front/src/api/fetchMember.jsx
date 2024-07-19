import axios from "axios";

const API_URL = "http://localhost:8080";

export const loginMember = async (loginDTO) => {
  try {
    const response = await axios.post(`${API_URL}/member/login`, loginDTO);
    return response.data;
  } catch (error) {
    console.error("Error logging in:", error);
    throw error;
  }
};

export const registerMember = async (registerDTO) => {
  try {
    const response = await axios.post(`${API_URL}/member/register`, registerDTO);
    return response.data;
  } catch (error) {
    console.error("Error registering:", error);
    throw error;
  }
};

export const viewMember = async (id) => {
  try {
    const response = await axios.get(`${API_URL}/member/view/${id}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching member:", error);
    throw error;
  }
};

export const updateMember = async (id, updateDTO) => {
  try {
    const response = await axios.put(`${API_URL}/member/update/${id}`, updateDTO);
    return response.data;
  } catch (error) {
    console.error("Error fetching member:", error);
    throw error;
  }
};
