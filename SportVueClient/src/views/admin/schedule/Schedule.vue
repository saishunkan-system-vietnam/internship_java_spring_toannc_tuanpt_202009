<template>
  <b-container fluid>
    <b-overlay
      :show="busy"
      rounded
      opacity="0.6"
      spinner-small
      spinner-variant="primary"
    >
      <v-breadcrumbs :items="itemlinks">
        <template v-slot:divider>
          <v-icon>mdi-chevron-right</v-icon>
        </template>
      </v-breadcrumbs>
      <h1 class="text-center">Schedule</h1>
      <v-btn
        class="mx-2"
        style="margin-bottom: 50px"
        dark
        color="indigo"
        @click.stop="dialog = true"
      >
        <v-icon dark> mdi-plus </v-icon></v-btn
      >
      <b-row>
        <b-col cols="12" sm="3">
          Title Schedule
          <b-form-input
            v-model="textTitleSchedule"
            placeholder="Input Field"
          ></b-form-input>
        </b-col>
        <b-col cols="12" sm="3">
          Team
          <b-form-input
            v-model="textTeam"
            placeholder="Input Field"
          ></b-form-input>
        </b-col>
        <b-col cols="12" sm="3">
          Status
          <b-form-select v-model="selectedStatus" :options="optionsStatus">
          </b-form-select>
        </b-col>
        <b-col cols="12" sm="3">
          Type
          <b-form-select v-model="selectedType" :options="optionsType">
          </b-form-select>
        </b-col>
      </b-row>
      <b-row>
        <b-col cols="12" sm="10">
          Time Start To
          <b-row>
            <b-col cols="12" sm="5">
              <b-form-datepicker
                id="example-datepicker"
                v-model="valueStart"
                class="mb-2"
                reset-button
              >
              </b-form-datepicker
            ></b-col>
            <b-col cols="12" sm="1" class="text-center">~</b-col>
            <b-col cols="12" sm="5">
              <b-form-datepicker
                id="example-datepicker1"
                v-model="valueEnd"
                class="mb-2"
                reset-button
              ></b-form-datepicker
            ></b-col>
          </b-row>
        </b-col>
        <b-col>
          <br />
          <b-row>
            <b-col>
              <b-button variant="primary" @click="search">Search</b-button>
            </b-col>
          </b-row>
        </b-col>
      </b-row>

      <b-table
        id="my-table"
        :per-page="perPage"
        :current-page="currentPage"
        striped
        hover
        :items="items"
        :fields="fields"
        small
      >
        <template v-slot:cell(Acction)="row">
          <router-link
            :to="{ path: '/DetailSchedule/' + row.item.idSchedule }"
            id="${schedule.idSchedule}"
          >
            <v-btn fab x-small>
              <v-icon>mdi-forward</v-icon>
            </v-btn>
          </router-link>
          <v-btn
            v-if="row.item.status == 0"
            fab
            x-small
            @click="editSchedule(row.item)"
          >
            <v-icon dark>mdi-pencil</v-icon>
          </v-btn>
          <v-btn
            v-if="row.item.status == 0"
            fab
            x-small
            @click="deleteSchedule(row.item)"
          >
            <v-icon dark>mdi-delete</v-icon>
          </v-btn>
        </template>
        <template v-slot:cell(status)="row">
          <p
            :style="
              row.item.status == 0
                ? 'color:green'
                : row.item.status == 1
                ? 'color:blue'
                : 'color:red'
            "
          >
            {{
              row.item.status == 0
                ? "Upcomming"
                : row.item.status == 1
                ? "On Game"
                : "Finished"
            }}
          </p>
        </template>
        <template v-slot:cell(score)="row">
          <p v-if="row.item.status == 2 && row.item.video != null">
            {{ row.item.scoreTeam1 }}-{{ row.item.scoreTeam2 }}
          </p>
          <p v-else>&ensp;-</p>
        </template>
      </b-table>
      <v-dialog v-model="dialogEdit" max-width="1000px">
        <v-card>
          <v-card-title class="headline"> Edit Schedule</v-card-title>

          <v-card-text>
            <EditSchedule
              :hideModal="hideModal"
              :getData="getData"
              :dataEdit="dataEdit"
            ></EditSchedule>
          </v-card-text>
        </v-card>
      </v-dialog>
      <b-modal id="modal-1" title="Delete" @ok="handleOk()">
        <p class="my-4">Are you sure!</p>
      </b-modal>
      <v-dialog v-model="dialog" max-width="1000px" persistent>
        <v-card>
          <v-card-title class="headline"> Create Schedule </v-card-title>

          <v-card-text>
            <AddSchedule
              :hideModal="hideModal"
              :getData="getData"
            ></AddSchedule>
          </v-card-text>
        </v-card>
      </v-dialog>
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
      ></b-pagination>
    </b-overlay>
  </b-container>
</template>
<script>
import AddSchedule from "./AddSchedule";
import EditSchedule from "./EditSchedule";

export default {
  components: {
    EditSchedule,
    AddSchedule,
  },
  data() {
    return {
      itemlinks: [
        {
          text: "Dashboard",
          disabled: false,
          href: "/admin/home",
        },
        {
          text:"Schedule",
          disabled:true
        }
      ],
      busy: true,
      optionsStatus: [
        { value: null, text: "Please select an option" },
        { value: "0", text: "Upcomming" },
        { value: "1", text: "On Game" },
        { value: "2", text: "Finished" },
      ],
      optionsType: [
        { value: null, text: "Please select an option" },
        { value: "Football", text: "Football" },
        { value: "TableTennis", text: "TableTennis" },
        { value: "BasketBall", text: "BasketBall" },
      ],
      textTitleSchedule: "",
      selectedStatus: null,
      valueStart: "",
      valueEnd: "",
      selectedType: null,
      perPage: 10,
      currentPage: 1,
      dataEdit: {},
      loading: false,
      rows: "",
      index: "",
      idDelete: "",
      dialog: false,
      dialogEdit: false,
      fields: [
        {
          key: "title",
          label: "Title",
          sortable: true,
        },
        {
          key: "team[0].type",
          label: "Type",
          sortable: true,
        },
        {
          label: "Name Tour",
          key: "nameTour",
          sortable: true,
        },
        {
          key: "timeStart",
          label: "Time Start",
          sortable: true,
        },

        {
          key: "team[0].nameTeam",
          label: "HomeTeam",
          sortable: true,
        },
        {
          key: "team[1].nameTeam",
          label: "Visiting Team",
          sortable: true,
        },

        {
          key: "score",
          label: "Score",
          sortable: false,
        },
        {
          key: "status",
          label: "Status",
          sortable: true,
        },
        "Acction",
      ],
      items: [],
      schedule: [],
      textTeam: "",
    };
  },
  created() {
    this.$store.dispatch("schedule/statusNow").then((response) => {
      this.loading = true;
      this.getData();
    });
  },
  methods: {
    search() {
      this.busy = false;
      var arrSearch = [];
      {
        if (this.textTitleSchedule != "") {
          this.schedule.forEach((element) => {
            if (element.title.includes(this.textTitleSchedule)) {
              arrSearch.push(element);
            }
          });
        } else {
          arrSearch = this.schedule;
        }
      }
      if (this.selectedStatus != null) {
        var arrStatus = [];
        arrSearch.forEach((element) => {
          if (element.status == this.selectedStatus) {
            arrStatus.push(element);
          }
        });
        arrSearch = arrStatus;
      }
      if (this.selectedType != null) {
        var arrType = [];
        arrSearch.forEach((element) => {
          if (element.team[0].type == this.selectedType) {
            arrType.push(element);
          }
        });
        arrSearch = arrType;
      }
      if (this.valueStart != "" && this.valueEnd != "") {
        var arrDate = [];
        arrSearch.forEach((element) => {
          if (
            element.timeStart >= this.valueStart &&
            element.timeStart <= this.valueEnd
          ) {
            arrDate.push(element);
          }
        });
        arrSearch = arrDate;
      }
      if (this.textTeam != "") {
        var arrTeam = [];
        arrSearch.forEach((element) => {
          if (
            element.team[0].nameTeam.includes(this.textTeam) ||
            element.team[1].nameTeam.includes(this.textTeam)
          ) {
            arrTeam.push(element);
          }
        });
        arrSearch = arrTeam;
      }
      this.items = arrSearch;
      this.rows = this.items.length;
    },
    hideModal() {
      this.dialog = false;
      this.dialogEdit = false;
    },
    deleteSchedule(id) {
      this.$bvModal.show("modal-1");
      this.index = this.items.indexOf(id);
      this.idDelete = id.idSchedule;
    },
    handleOk() {
      this.$store
        .dispatch("schedule/deleteSchedule", this.idDelete)
        .then((response) => {
          alert(response.data);
          this.items.splice(this.index);
          this.getData();
        });
    },
    editSchedule(id) {
      this.dataEdit = id;
      this.dialogEdit = true;
    },
    getData() {
      this.$store.dispatch("schedule/getAll").then((response) => {
        this.rows = response.data.length;
        this.items = response.data;
        this.schedule = response.data;
        this.busy = false;
      });
    },
  },
};
</script>
