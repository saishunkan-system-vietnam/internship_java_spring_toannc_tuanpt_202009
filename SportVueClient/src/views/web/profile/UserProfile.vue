<template>
  <v-card>
    <v-card-title>
      <span class="headline">User Profile</span> <v-spacer></v-spacer
      ><button @click="edit = true"><v-icon>mdi-pencil</v-icon></button>
    </v-card-title>
    <v-card-text>
      <v-container>
        <v-row>
          <v-col cols="6">
            <span v-if="edit == false">
              <v-img
                max-height="100"
                max-width="100"
                :src="this.info.profile.avatar"
              ></v-img>
            </span>
            <span v-else
              ><v-file-input
                prepend-icon="mdi-camera"
                accept="image/png, image/jpeg, image/bmp"
                placeholder="Pick an avatar"
                label="Avatar"
                v-model="image"
              ></v-file-input
            ></span>
          </v-col>
          <v-col cols="12" sm="6" md="6">
            <h4 v-if="edit == false">Name:{{ this.info.profile.name }}</h4>
            <h4 v-else><v-text-field label="Name" v-model="name"></v-text-field></h4>
          </v-col>
          <v-col cols="12" sm="6" md="6">
              <v-icon>mdi-email</v-icon>{{ this.info.email }}
          </v-col>
          <v-col cols="6">
            <span v-if="edit == false">
              <v-icon>mdi-phone</v-icon>{{ this.info.profile.phone }}</span
            >
            <span v-else><v-text-field label="Phone" v-model="phone"></v-text-field></span>
          </v-col>
        </v-row>
      </v-container>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue darken-1" text @click="Ok" v-if="edit==true"> OK </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
export default {
  props: {
    controlModalUser: {
      type: Function,
    },
    showMenu:Boolean
  },
  data() {
    return {
      info: "",
      edit: false,
      image:{},
      name:'',
      phone:''

    };
  },
  created() {
    this.info = this.$store.state.user.userInfo;
    console.log(this.info);
  },
  methods:{
    Ok(){
      
      var profile = new FormData();
      profile.append("name", this.name);
      profile.append("phone", this.phone);
      profile.append("file", this.image);
      profile.append("email",this.info.email)
      this.$store.dispatch('user/updateProfileUser',profile).then(res=>{
        this.image={},
        this.name='',
        this.phone='';
        this.edit=false;
      this.controlModalUser();

      })
    }
  },
 
};
</script>