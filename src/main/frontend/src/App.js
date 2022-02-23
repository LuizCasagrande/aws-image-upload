import axios from "axios";
import React, { useCallback, useEffect, useState } from "react";
import { useDropzone } from "react-dropzone";
import "./App.css";

const UserProfiles = () => {
  const [UserProfiles, setUserProfiles] = useState([]);

  const fetchUserProfiles = () => {
    axios.get("http://localhost:8080/api/v1/user-profile").then((r) => {
      console.log(r);
      setUserProfiles(r.data);
    });
  };

  useEffect(() => {
    fetchUserProfiles();
  }, []);

  return UserProfiles.map((userProfile, index) => {
    return (
      <div key={index}>
        {userProfile.id ? (
          <img
            src={`http://localhost:8080/api/v1/user-profile/${userProfile.id}/image/download`}
            alt="profile_img"
          />
        ) : null}
        <br />
        <br />
        <h1>{userProfile.name}</h1>
        <p>{userProfile.id}</p>
        <Dropzone {...userProfile} />
        <br />
      </div>
    );
  });
};

function Dropzone({ id }) {
  const onDrop = useCallback(
    (acceptedFiles) => {
      const file = acceptedFiles[0];

      console.log(file);

      const formData = new FormData();
      formData.append("file", file);

      axios
        .post(
          `http://localhost:8080/api/v1/user-profile/${id}/image/upload`,
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        )
        .then(() => {
          console.log("file uploaded successfully");
          window.location.reload();
        })
        .catch((e) => console.log(e));
    },
    [id]
  );
  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {isDragActive ? (
        <p>Drop the image here ...</p>
      ) : (
        <p>Drag 'n' drop profile image, or click to select profile image</p>
      )}
    </div>
  );
}

function App() {
  return (
    <div className="App">
      <UserProfiles />
    </div>
  );
}

export default App;
