import axiosInstance from "./axiosInstance";

export const writeReply = async (replyDTO) => {
  try {
    const response = await axiosInstance.post("/reply/write", replyDTO);
    return response.data;
  } catch (error) {
    console.error("Error writing reply:", error);
    throw error;
  }
};

export const updateReply = async (id, updateDTO) => {
  try {
    const response = await axiosInstance.put(`/reply/update/${id}`, updateDTO);
    return response.data;
  } catch (error) {
    console.error("Error updating reply:", error);
    throw error;
  }
};

export const deleteReply = async (id) => {
  try {
    const response = await axiosInstance.delete(`/reply/delete/${id}`);
    return response.data;
  } catch (error) {
    console.error("Error deleting reply:", error);
    throw error;
  }
};
