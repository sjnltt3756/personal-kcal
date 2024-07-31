import axiosInstance from './axiosInstance';

// 게시글 작성
export const createBoard = async (boardData) => {
    const response = await axiosInstance.post('/board/write', boardData);
    return response.data;
};

// 게시글 목록 조회
export const getBoardList = async (page, size) => {
    const response = await axiosInstance.get('/board/list', {
        params: { page, size },
    });
    return response.data;
};

// 특정 게시글 조회
export const viewBoard = async (boardId) => {
    const response = await axiosInstance.get(`/board/view/${boardId}`);
    return response.data;
};

// 게시글 수정
export const updateBoard = async (boardId, boardData) => {
    const response = await axiosInstance.put(`/board/update/${boardId}`, boardData);
    return response.data;
};

// 게시글 삭제
export const deleteBoard = async (boardId) => {
    const response = await axiosInstance.delete(`/board/delete/${boardId}`);
    return response.data;
};
